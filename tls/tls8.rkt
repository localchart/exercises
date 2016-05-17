#lang racket
(require racket/trace)

(define rember-f
  (lambda (test?)
    (lambda (a l)
      (cond
       [(null? l) '()]
       [(test? a (car l)) (cdr l)]
       [else (cons (car l) ((rember-f test?) a (cdr l)))]))))

(define rember-eq? (rember-f eq?))

(rember-eq? 'tuna '(tuna salad is good))

(define insertL-f
  (lambda (test?)
    (lambda (new old l)
      (cond
       [(null? l) '()]
       [(test? old (car l)) (cons new l)]
       [else (cons (car l)
                   ((insertL-f test?) new old (cdr l)))]))))

(define insertR-f
  (lambda (test?)
    (lambda (new old l)
      (cond
       [(null? l) '()]
       [(test? old (car l)) (cons old (cons new (cdr l)))]
       [else (cons (car l)
                   ((insertR-f test?) new old (cdr l)))]))))

((insertL-f =) 1 3 '(6 3 8))
((insertR-f =) 1 3 '(6 3 8))

(define insert-g
  (lambda (g)
    (lambda (new old l)
      (cond
       [(null? l) '()]
       [(eq? old (car l)) (g new old (cdr l))]
       [else (cons (car l)
                   ((insert-g g) new old (cdr l)))]))))

(define insert-L
  (insert-g (lambda (new old l)
              (cons new (cons old l)))))

(define insert-R
  (insert-g (lambda (new old l)
              (cons old (cons new l)))))

(insert-R 1 4 '(3 4 5))
(insert-L 1 4 '(3 4 5))

(define subst
  (lambda (new old l)
    (cond
     [(null? l) '()]
     [(eq? old (car l)) (cons new (cdr l))]
     [else (cons (car l) (subst new old (cdr l)))])))

(subst 2 1 '(1 3 4))

(define subst1
  (insert-g (lambda (new old l)
              (cons new l))))

(subst1 2 1 '(1 3 4))

(define rember1
  (insert-g (lambda (new old l) l)))

(rember1 #f 'sausage '(pizza with sausage and bacon))

(define multirember
  (lambda (a lat)
    (cond
     [(null? lat) '()]
     [(eq? a (car lat)) (multirember a (cdr lat))]
     [else (cons (car lat) (multirember a (cdr lat)))])))

(define multirember-f
  (lambda (test?)
    (lambda (a lat)
      (cond
       [(null? lat) '()]
       [(test? a (car lat)) ((multirember-f test?) a (cdr lat))]
       [else (cons (car lat)
                   ((multirember-f test?) a (cdr lat)))]))))

((multirember-f eq?) 'tuna '(shrimp salad tuna salad and tuna))

(define multirember-eq?
  (multirember-f eq?))

(define multiremberT
  (lambda (test? lat)
    (cond
     [(null? lat) '()]
     [(test? (car lat)) (multiremberT test? (cdr lat))]
     [else (cons (car lat) (multiremberT test? (cdr lat)))])))

(multiremberT (lambda (a)
                (eq? a 'tuna))
              '(shrimp salad tuna salad and tuna))

(define multirember&co
  (lambda (a lat col)
    (cond
     [(null? lat) (col '() '())]
     [(eq? a (car lat))
      (multirember&co a (cdr lat)
                      (lambda (newlat seen)
                        (col newlat
                             (cons (car lat) seen))))]
     [else
      (multirember&co a (cdr lat)
                      (lambda (newlat seen)
                        (col (cons (car lat) newlat)
                             seen)))])))

(define multiinsertL
  (lambda (new old lat)
    (cond
     [(null? lat) '()]
     [(eq? old (car lat))
      (cons new
            (cons old (multiinsertL new old (cdr lat))))]
     [else
      (cons (car lat) (multiinsertL new old (cdr lat)))])))

(define multiinsertR
  (lambda (new old lat)
    (cond
     [(null? lat) '()]
     [(eq? old (car lat))
      (cons old
            (cons new (multiinsertR new old (cdr lat))))]
     [else
      (cons (car lat) (multiinsertR new old (cdr lat)))])))

(define multiinsertLR
  (lambda (new oldL oldR lat)
    (cond
     [(null? lat) '()]
     [(eq? oldL (car lat))
      (cons new
            (cons oldL (multiinsertLR new oldL oldR (cdr lat))))]
     [(eq? oldR (car lat))
      (cons oldR
            (cons new (multiinsertLR new oldL oldR (cdr lat))))]
     [else
      (cons (car lat) (multiinsertLR new oldL oldR (cdr lat)))])))

(define multiinsertLR&co
  (lambda (new oldL oldR lat col)
    (cond
     [(null? lat) (col '() 0 0)]
     [(eq? oldL (car lat))
      (multiinsertLR&co new oldL oldR (cdr lat)
                        (lambda (newlat L R)
                          (col (cons new (cons oldL newlat))
                               (add1 L) R)))]
     [(eq? oldR (car lat))
      (multiinsertLR&co new oldL oldR (cdr lat)
                        (lambda (newlat L R)
                          (col (cons oldR (cons new newlat))
                               L (add1 R))))]
     [else
      (multiinsertLR&co new oldL oldR (cdr lat)
                        (lambda (newlat L R)
                          (col (cons (car lat) newlat) L R)))])))

(multiinsertLR&co 'salty 'fish 'chips
                  '(chips and fish or fish and chips)
                  (lambda (newlat L R)
                    (list newlat L R)))
(define even?
  (lambda (n)
    (= (* 2 (/ n 2))) n))

(define atom?
  (lambda (n)
    (not (or (pair? n) (null? n)))))

(define even-only*
  (lambda (l)
    (cond
     [(null? l) '()]
     [(atom? (car l))
      (if (even? (car l))
          (cons (car l) (even-only* (cdr l)))
          (even-only* (cdr l)))]
     [else
      (cons (even-only* (car l))
            (even-only* (cdr l)))])))

(even-only* '((9 1 2 8) 3 10 ((9 9) 7 6) 2))

(define even-only*&co
  (lambda (l col)
    (cond
     [(null? l) (col '() 1 0)]
     [(atom? (car l))
      (cond
       [(even? (car l))
        (even-only*&co (cdr l)
                       (lambda (newl p s)
                         (col (cons (car l) newl) (* (car l) p) s)))]
       [else
        (even-only*&co (cdr l)
                       (lambda (newl p s)
                         (col newl p (+ (car l) s))))])]
     [else
      (even-only*&co (car l)
                     (lambda (l1 p1 s1)
                       (even-only*&co (cdr l)
                                      (lambda (l2 p2 s2)
                                        (col (cons l1 l2)
                                             (* p1 p2)
                                             (+ s1 s2))))))])))

(define the-last-friend
  (lambda (newl p s)
    (cons s (cons p newl))))

(even-only*&co '((9 1 2 8) 3 10 ((9 9) 7 6) 2) the-last-friend)
