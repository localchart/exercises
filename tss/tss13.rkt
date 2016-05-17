#lang racket

(define member?
  (lambda (a lat)
    (cond
     [(null? lat) #f]
     [(eq? a (car lat)) #t]
     [else
      (member? a (cdr lat))])))

(define intersect
  (lambda (set1 set2)
    (cond
     [(null? set1) (quote ())]
     [(member? (car set1) set2)
      (cons (car set1)
            (intersect (cdr set1) set2))]
     [else
      (intersect (cdr set1) set2)])))

(intersect '(a b c) '(b d e))

(define intersect-letrec
  (lambda (set1 set2)
    (letrec
        ([I (lambda (set)
              (cond
               [(null? set) (quote ())]
               [(member? (car set) set2)
                (cons (car set)
                      (I (cdr set)))]
               [else (I (cdr set))]))])
      (I set1))))

(intersect-letrec '(a b c) '(d e f c))

(define intersectall
  (lambda (lset)
    (cond
     [(null? lset) (quote ())]
     [(null? (cdr lset)) (car lset)]
     [else
      (intersect-letrec (car lset)
                        (intersectall (cdr lset)))])))

(define intersectall-letrec
  (lambda (lset)
    (letrec
        ([A (lambda (lset)
              (cond
               [(null? (cdr lset)) (car lset)]
               [else
                (intersect-letrec (car lset)
                                  (A (cdr lset)))]))])
      (cond
       [(null? lset) (quote ())]
       [else (A lset)]))))

(define lset '((3 4 5)
               (4 5)
               (3 8 4 9)))

(intersectall lset)
(intersectall-letrec lset)

(define intersectall-letcc
  (lambda (lset)
    (call/cc
     (lambda (hop)
       (letrec
           ([A (lambda (lset)
                 (cond
                  [(null? (car lset)) (hop (quote ()))]
                  [(null? (cdr lset)) (car lset)]
                  [else
                   (intersect-letrec (car lset)
                                     (A (cdr lset)))]))])
         (cond
          [(null? lset) (quote ())]
          [else (A lset)]))))))

(intersectall-letcc lset)

(define intersectall-final
  (lambda (lset)
    (call/cc
     (lambda (hop)
       (letrec
           ([A (lambda (lset)
                 (cond
                  [(null? (car lset)) (hop (quote ()))]
                  [(null? (cdr lset)) (car lset)]
                  [else
                   (I (car lset)
                      (A (cdr lset)))]))]
            [I (lambda (set1 set2)
                 (letrec
                     ([J (lambda (set)
                           (cond
                            [(null? set) (quote ())]
                            [(member? (car set) set2)
                             (cons (car set)
                                   (J (cdr set)))]
                            [else
                             (J (cdr set))]))])
                   (cond
                    [(null? set2) (hop (quote ()))]
                    [else (J set1)])))])
         (cond
          [(null? lset) (quote ())]
          [else (A lset)]))))))

(intersectall-final lset)

(define rember
  (lambda (a lat)
    (letrec
        ([R (lambda (lat)
              (cond
               [(null? lat) (quote ())]
               [(eq? a (car lat))
                (cdr lat)]
               [else
                (cons (car lat)
                      (R (cdr lat)))]))])
      (R lat))))

(rember 'a '(1 2 3 a 4 a 5))

(define rember-beyond-first
  (lambda (a lat)
    (letrec
        ([R (lambda (lat)
              (cond
               [(null? lat) (quote ())]
               [(eq? a (car lat))
                (quote ())]
               [else
                (cons (car lat)
                      (R (cdr lat)))]))])
      (R lat))))
(rember-beyond-first 4 '(1 2 3 a 4 a 5))

(define rember-upto-last
  (lambda (a lat)
    (call/cc
     (lambda (skip)
       (letrec
           ([R (lambda (lat)
                 (cond
                  [(null? lat) (quote ())]
                  [(eq? a (car lat))
                   (skip (R (cdr lat)))]
                  [else
                   (cons (car lat)
                         (R (cdr lat)))]))])
         (R lat))))))
(rember-upto-last 'a '(1 2 3 a 4 a 5))
