#lang racket

;; in-S?
(define in-S?
  (lambda (n)
    (cond
     [(zero? n) #t]
     [(positive? (- n 3)) (in-S? (- n 3))]
     [else #f])))
