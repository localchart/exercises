#lang racket

(define two-in-a-row
  (lambda (lat)
    (cond
     [(null? lat) #f]
     [else
      (or (is-first? (car lat) (cdr lat))
          (two-in-a-row (cdr lat)))])))

(define (is-first? a lat)
  (cond
   [(null? lat) #f]
   [else
    (eq? a (car lat))]))

;; test
(two-in-a-row '(a b c b))
(two-in-a-row '(a b c c))

(define two-in-a-row?
  (lambda (lat)
    (cond
     [(null? lat) #f]
     [else
      (is-first-b? (car lat) (cdr lat))])))

(define is-first-b?
  (lambda (a lat)
    (cond
     [(null? lat) #f]
     [(eq? a (car lat)) #t]
     [else (two-in-a-row? lat)])))

;; test two-in-a-row?
(two-in-a-row? '(a b c d))
(two-in-a-row? '(a b c c))

(define two-in-a-row-b?
  (lambda (preceding lat)
    (cond
     [(null? lat) #f]
     [else
      (or (eq? preceding (car lat))
          (two-in-a-row-b? (car lat) (cdr lat)))])))

(define two-in-a-row-final?
  (lambda (lat)
    (cond
     [(null? lat) #f]
     (else two-in-a-row-b? (car lat) (cdr lat)))))

(define (sum-of-prefixes tup)
  (cond
   [(null? tup) (quote ())]
   [else
    (sum-of-prefixes-help 0 tup)]))

(define sum-of-prefixes-help
  (lambda (a tup)
    (cond
     [(null? tup) (quote ())]
     [else
      (cons (+ a (car tup))
            (sum-of-prefixes-help (+ a (car tup))
                                  (cdr tup)))])))

(sum-of-prefixes '(1 1 1 1 1))

(define pick
  (lambda (n lat)
    (cond
     [(null? lat) (quote ())]
     [(or (zero? n) (eq? n 1)) (car lat)]
     [else
      (pick (sub1 n) (cdr lat))])))

(pick 2 '(1 2 3 4))

(define scramble
  (lambda (tup)
    (scramble-help tup (quote ()))))

(define scramble-help
  (lambda (tup rev-pre)
    (cond
     [(null? tup) (quote ())]
     [else
      (cons (pick (car tup)
                  (cons (car tup) rev-pre))
            (scramble-help (cdr tup)
                           (cons (car tup) rev-pre)))])))

(scramble '(1 2 3 4 5 5 6 6))
