#lang racket
(require "1.21.rkt")
(require "fermat-test.rkt")


(define (time-prime-test n)
  (newline)
  (display n)
  (start-test n (current-inexact-milliseconds)))

(define (start-test n rt)
  (if (prime? n)
      (report-prime (- (current-inexact-milliseconds) rt))
      false))

(define (report-prime time)
  (display "  ***  ")
  (display time))

(define (search-for-primes from)
  (define (f n next lst)
    (if (= n 0)
        lst
        (let* ([p (time-prime-test next)] [x (+ next 1)])
          (if p
              (f (- n 1) x (cons p lst))
              (f n x lst)))))
  (f 30 from '()))
