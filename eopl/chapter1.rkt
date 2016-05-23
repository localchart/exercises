#lang racket

;; in-S? : N -> bool
;; usage: (in-S? n) = #t if n is in S, #f otherwise
(define in-S?
  (lambda (n)
    (cond
     [(zero? n) #t]
     [(positive? (- n 3)) (in-S? (- n 3))]
     [(zero? (- n 3)) (in-S? (- n 3))]
     [else #f])))
