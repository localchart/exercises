#lang racket
(require "sicp.rkt")
(define (xx a b)
  (cond ((= b 0) 0)
        ((= b 1) a)
        ((even? b) (double (xx a (halve b))))
        (else (+ a (xx a (- b 1))))))
