#lang racket

(define atom?
  (lambda (s)
    (and (not (null? s))
         (not (pair? s)))))

(define member?
  (lambda (a lat)
    (letrec
        ([M (lambda (lat)
              (cond
               [(null? lat) #f]
               [(eq? a (car lat)) #t]
               [else
                (M (cdr lat))]))])
      (M lat))))

(define sweet-tooth
  (lambda (food)
    (cons food
          (cons (quote cake)
                (quote ())))))

(define last (quote angelfood))
(define ingredients (quote ()))

(define sweet-toothL
  (lambda (food)
    (set! last food)
    (set! ingredients (cons food ingredients))
    (display last)
    (cons food
          (cons (quote cake)
                (quote ())))))

(sweet-toothL 'chocolate)
(sweet-toothL 'c)
(sweet-toothL 'h)
(display ingredients)

(define deep
  (lambda (n)
    (cond
     [(zero? n) (quote pizza)]
     [else
      (cons (deep (sub1 n))
            (quote ()))])))
(deep 0)

(define Ns (quote ()))
(define Rs (quote ()))

(define deepR
  (lambda (n)
    (let ([rs (deep n)])
      (set! Ns (cons n Ns))
      (set! Rs (cons rs Rs))
      rs)))

(deepR 3)
(deepR 5)
(deepR 3)

(define find
  (lambda (n Ns Rs)
    (letrec
        ([F (lambda (Ns Rs)
              (cond
               [(null? Ns) #f]
               [(= (car Ns) n) (car Rs)]
               [else
                (F (cdr Ns) (cdr Rs))]))])
      (F Ns Rs))))

(find 5 Ns Rs)

(define deepM
  (lambda (n)
    (if (member? n Ns)
        (find n Ns Rs)
        (let ([rs (deep n)])
          (set! Ns (cons n Ns))
          (set! Rs (cons rs Rs))
          rs))))

(set! Ns (cdr Ns))
(set! Rs (cdr Rs))

(define deep2
  (lambda (n)
    (cond
     [(zero? n) (quote ())]
     [else
      (cons (deepM2 (sub1 n))
            (quote ()))])))

(define deepM2
  (lambda (n)
    (if (member? n Ns)
        (find n Ns Rs)
        (let ([rs (deep2 n)])
          (set! Ns (cons n Ns))
          (set! Rs (cons rs Rs))
          rs))))

(deepM2 9)
(display (list Ns Rs))
