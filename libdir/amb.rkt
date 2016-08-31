#lang racket
(define test-amb
  (λ (a b)
    (if (< a b)
        (list a b)
        (amb))))

(define fail-link '())
(define amb-fail
  (λ ()
    (if (null? fail-link)
        (error "amb process failed")
        (let ([prev (car fail-link)])
          (set! fail-link (cdr fail-link))
          (prev)))))
(define (amb . ls)
  (define (do-amb success cur)
    (if (null? cur)
        (amb-fail)
        (begin
          (call/cc
           (λ (fail)
             (set! fail-link (cons fail fail-link))
             (success (car cur))))
          (do-amb success (cdr cur)))))
  (call/cc
   (λ (success)
     (do-amb success ls))))
(test-amb (amb 4 2 1) (amb 1 2 2))