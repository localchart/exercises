#lang racket
(require "sicp.rkt")
(define (search f left right)
  (let ([mid (average left right)])
    (if (close-enough? left right)
        mid
        (let ([test-val (f mid)])
          (cond [(positive? test-val)
                 (search f left mid)]
                [(negative? test-val)
                 (search f mid right)]
                [else mid])))))

(define (close-enough? x y)
  (< (abs (- x y)) 0.001))

(define (half-interval-method f a b)
  (let* ([a-val (f a)]
         [b-val (f b)])
    (cond [(and (positive? a-val) (negative? b-val))
           (search f b a)]
          [(and (positive? b-val) (negative? a-val))
           (search f a b)]
          [else (error "Values are not of opposite sign" a b)])))