#lang racket
(require racket/trace)

(define pick
  (lambda (n l)
    (cond
     [(null? l) '()]
     [(or (= n 0) (= n 1)) (car l)]
     [else (pick (- n 1) (cdr l))])))

(pick 4 '(6 2 4 caviar 5 7 3))

(define keep-looking
  (lambda (a n lat)
    (cond
     [(number? n) (keep-looking a (pick n lat) lat)]
     [else (eq? a n)])))

(define looking
  (lambda (a lat)
    (keep-looking a (pick 1 lat) lat)))

(looking 'caviar '(6 2 4 caviar 5 7 3))
(looking 'caviar '(6 2 pare caviar 5 7 3))

(define atom?
  (lambda (n)
    (not (or (pair? n) (null? n)))))

(define eternity
  (lambda (x)
    (eternity x)))

(define shift
  (lambda (l)
    (cond
     [(null? l) '()]
     [(atom? (car l)) (cons (car l) (shift (cdr l)))]
     [else
      (cons (car (car l))
            (cons
             (cons
              (car (cdr (car l)))
              (cdr l))
             '()))])))

(shift '((a b) c))
(shift '((a b) (c d)))

(define A
  (lambda (n m)
    (cond
     [(zero? n) (add1 m)]
     [(zero? m) (A (sub1 n) 1)]
     [else
      (A (sub1 n) (A n (sub1 m)))])))

(A 1 0)
(A 1 1)

(define length
  (lambda (l)
    (cond
     [(null? l) 0]
     [else
      (add1 (length (cdr l)))])))
(length '(1 2 3 4))

(lambda (l)
  (cond
   [(null? l) 0]
   [else
    (add1 (eternity (cdr l)))]))

;; length0
((lambda (len)
   (lambda (l)
     (cond
      [(null? l) 0]
      [else
       (add1 (len (cdr l)))])))
 eternity)

;; length1
((lambda (len)
   (lambda (l)
     (cond
      [(null? l) 0]
      [else
       (add1 (len (cdr l)))])))
 ((lambda (len)
    (lambda (l)
      (cond
       [(null? l) 0]
       [else
        (add1 (len (cdr l)))])))
  eternity))

;; length2
((lambda (len)
   (lambda (l)
     (cond
      [(null? l) 0]
      [else
       (add1 (len (cdr l)))])))
 ((lambda (len)
    (lambda (l)
      (cond
       [(null? l) 0]
       [else
        (add1 (len (cdr l)))])))
  ((lambda (len)
     (lambda (l)
       (cond
        [(null? l) 0]
        [else
         (add1 (len (cdr l)))])))
   eternity)))

;; length0
((lambda (make-length)
   (make-length eternity))
 (lambda (len)
   (lambda (l)
     (cond
      [(null? l) 0]
      [else
       (add1 (len (cdr l)))]))))

;; length1
(((lambda (make-length)
    (make-length
     (make-length eternity)))
  (lambda (len)
    (lambda (l)
      (cond
       [(null? l) 0]
       [else
        (add1 (len (cdr l)))]))))
 '(1))

;; length2
(((lambda (make-length)
    (make-length
     (make-length
      (make-length eternity))))
  (lambda (len)
    (lambda (l)
      (cond
       [(null? l) 0]
       [else
        (add1 (len (cdr l)))]))))
 '(1 2))

;; still length0
((lambda (make-length)
   (make-length make-length))
 (lambda (make-length)
   (lambda (l)
     (cond
      [(null? l) 0]
      [else
       (add1 (make-length (cdr l)))]))))

;; length1
(((lambda (make-length)
    (make-length make-length))
  (lambda (make-length)
    (lambda (l)
      (cond
       [(null? l) 0]
       [else
        (add1 ((make-length eternity) (cdr l)))]))))
 '(1))

(((lambda (make-length)
    (make-length make-length))
  (lambda (make-length)
    (lambda (l)
      (cond
       [(null? l) 0]
       [else
        (add1 ((make-length make-length) (cdr l)))]))))
 '(1 2))

(define Y
  (lambda (f)
    ((lambda (g) (g g))
     (lambda (g)
       (f (lambda (v) ((g g) v)))))))

(define Y-length
  (Y (lambda (len)
       (lambda (l)
         (cond
          [(null? l) 0]
          [else
           (add1 (len (cdr l)))])))))
(Y-length '(1 2 3 9 3))
