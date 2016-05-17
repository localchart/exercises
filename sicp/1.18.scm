#lang racket
(require "sicp.rkt")

(define (f a b rs)
  (cond ((= b 0) rs)
        ((even? b) (f (double a) (halve b) rs))
        (else (f a (- b 1) (+ a rs)))))

(define (x a b)
  (f a b 0))
