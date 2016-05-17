#lang racket
(define Y
  (λ (f)
    ((λ (s) (s s))
     (λ (x)
       (f (λ (v) ((x x) v)))))))
(define mylen
  (Y
   (λ (len)
     (λ (ls)
       (if (null? ls)
           0
           (add1 (len (cdr ls))))))))
