#lang racket

(define x null)

(define gourmind
  (lambda (food)
    (set! x food)
    (cons food
          (cons food
                (quote ())))))

(gourmind 'a)
(display x)

(define diner
  (lambda (food)
    (cons (quote milkshake)
          (cons food
                (quote ())))))

(define dinerR
  (lambda (food)
    (set! x food)
    (cons (quote milkshake)
          (cons food
                (quote ())))))
(dinerR 'b)
(display x)

(define omnivore
  (let ([x (quote minestrone)])
    (lambda (food)
      (set! x food)
      (cons food
            (cons food
                  (quote ()))))))

(omnivore 'c)
(display x)

(define nibbler
  (lambda (food)
    (let ([x (quote donut)])
      (set! x food)
      (cons food
            (cons x
                  (quote ()))))))
(omnivore 'cheerio)
(display x)
(nibbler 'cheerio)

(define food (quote none))
(define glutton
  (lambda (x)
    (set! food x)
    (cons (quote more)
          (cons x
                (cons (quote more)
                      (cons x
                            (quote ())))))))
(glutton 'ads)

(define look-food
  (lambda ()
    (display food)
    (display "  ")
    (display x)))

(look-food)

(define chez-nous
  (lambda ()
    (let ([a x])
      (set! x food)
      (set! food a)
      (look-food))))

(chez-nous)
