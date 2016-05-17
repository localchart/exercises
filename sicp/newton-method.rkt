#lang racket
(require "sicp.rkt")
(provide fixed-point newton-method fixed-point-transform average-damp dx)

(define tolerance 0.00001)
(define (fixed-point f first-guess)
  (define (close-enough? a b)
    (< (abs (- a b)) tolerance))
  (define (try guess)
    (let ([next (f guess)])
      (if (close-enough? guess next)
          next
          (try next))))
  (try first-guess))

(define (average-damp f)
  (λ (x) (average x (f x))))

(define (sqrt x)
  (fixed-point (average-damp (lambda (y) (/ x y))) 1.0))


(define (deriv g)
  (λ (x) (/ (- (g (+ x dx)) (g x)) dx)))
(define dx 0.00001)

(define (newton-transform g)
  (λ (x) (- x (/ (g x) ((deriv g) x)))))

(define (newton-method g guess)
  (fixed-point (newton-transform g) guess))

(define (newton-sqrt x)
  (newton-method (λ (y)
                   (- x (square y)))
                 1.0))

(define (fixed-point-transform g transform guess)
  (fixed-point (transform g) guess))

(define (sqrt1 x)
  (fixed-point-transform (λ (y)
                           (- x (square y)))
                         newton-transform
                         1.0))


(define (sqrt2 x)
  (fixed-point-transform (λ (y)
                           (/ x y))
                         average-damp
                         1.0))
