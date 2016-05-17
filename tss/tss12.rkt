#lang racket

(define multirember-f
  (lambda (test?)
    (lambda (a lat)
      (cond
       [(null? lat) (quote ())]
       [(test? a (car lat))
        ((multirember-f test?) a (cdr lat))]
       [else
        (cons (car lat)
              ((multirember-f test?) a (cdr lat)))]))))

(define multirember-f-2
  (lambda (test?)
    (letrec
        ([m-f (lambda (a lat)
                (cond
                 [(null? lat) (quote ())]
                 [(test? a (car lat))
                  (m-f a (cdr lat))]
                 [else
                  (cons (car lat)
                        (m-f a (cdr lat)))]))])
      m-f)))

(define member?
  (lambda (a lat)
    (letrec
        ([yes? (lambda (lat)
                 (cond
                  [(null? lat) #f]
                  [(eq? a (car lat)) #t]
                  [else (yes? (cdr lat))]))])
      (yes? lat))))
(member? 'a '(a b c))
(member? 'd '(a b c))


(define union
  (lambda (set1 set2)
    (letrec
        ([u (lambda (set1 set2)
              (cond
               [(null? set1) set2]
               [(member? (car set1) set2)
                (u (cdr set1) set2)]
               [else
                (cons (car set1)
                      (u (cdr set1) set2))]))])
      (u set1 set2))))

(union '(a b c) '(d a f))

(define union-2
  (lambda (set1 set2)
    (letrec
        ([U (lambda (set1)
              (cond
               [(null? set1) set2]
               [(M? (car set1) set2)
                (U (cdr set1))]
               [else
                (cons (car set1)
                      (U (cdr set1)))]))]
         [M? (lambda (a lat)
               (cond
                [(null? lat) #f]
                [(eq? a (car lat)) #t]
                [else
                 (M? a (cdr lat))]))])
      (U set1))))

(union-2 '(a b c) '(d a f))

(define two-in-a-row?
  (letrec
      ([W (lambda (a lat)
            (cond
             [(null? lat) #f]
             [else
              (or (eq? (car lat) a)
                  (W (car lat) (cdr lat)))]))])
    (lambda (lat)
      (cond
       [(null? lat) #f]
       [else
        (W (car lat) (cdr lat))]))))

(two-in-a-row? '(a b 8 c d))

(define scramble
  (letrec
      ([pick (lambda (n lat)
               (cond
                [(null? lat) (quote ())]
                [(eq? n 1) (car lat)]
                [else
                 (pick (sub1 n) (cdr lat))]))]
       [P (lambda (tup col)
            (cond
             [(null? tup) (quote ())]
             [else
              (let ([rp (cons (car tup) col)])
                (cons (pick (car tup) rp)
                      (P (cdr tup) rp)))]))])
    (lambda (tup)
      (P tup (quote ())))))

(scramble '(1 2 3 4 5 6 6))
