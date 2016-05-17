#lang racket

(provide double halve square divides? average cube prime? gcd)

(define (double x)
  (+ x x))

(define (halve x)
  (/ x 2))
(define (divides? a b)
  (= 0 (remainder a b)))

(define (square x)
  (* x x))

(define (average x y)
  (/ (+ x y) 2))

(define (cube x)
  (* x x x))

(define (find-divisor n test)
  (cond
    [(> (square test) n) n]
    [(divides? n test) test]   
    [else (find-divisor n (next test))])
  )

(define (smallest-divisor n)
  (find-divisor n 2))

(define (next test)
  (cond
    [(= test 2) 3]
    [else (+ test 2)])
  )

(define (prime? n)
  (= n (smallest-divisor n))
  )

(define (gcd a b)
  (if (= b 0)
      a
      (gcd b (remainder a b))))