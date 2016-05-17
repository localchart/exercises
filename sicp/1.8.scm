(define (there-iter guess pre y)
  (if (good-enough? guess pre)
    guess
    (there-iter (improve guess y) guess y)))

(define (improve guess y)
    (three (/ y (square guess)) (* 2 guess)))
(define (three guess y)
  (/ (+ guess y) 3))
(define (good-enough? guess pre)
  (< (/ (abs (- guess pre)) pre) 0.0001))

(define (my-three y)
  (there-iter 1.0 0.5 y))
