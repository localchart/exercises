#lang racket
(require "sicp.rkt")

(define (new-if predicate then-clause else-clause)
  (cond (predicate then-clause)
	(else else-clause)))

(define (sqrt-iter guess pre x)
  (if (good-enough? guess pre)
      guess
      (sqrt-iter (improve guess x) guess x)))

(define (improve guess x)
  (average guess (/ x guess)))

(define (good-enough? guest pre)
  (< (/ (abs (- guest pre)) pre) 0.001))

(define (my-sqrt x)
  (sqrt-iter 1.0 0.5 x)) 
