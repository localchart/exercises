#lang racket
(require "sicp.rkt" "newton-method.rkt")
(define (cubic a b c)
  (λ (x)
    (+ (cube x) (* (square x) a) (* b x) c)))

(newton-method (cubic 3 3 1) 1)
