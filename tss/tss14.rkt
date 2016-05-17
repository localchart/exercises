#lang racket

(define atom?
  (lambda (s)
    (and (not (null? s))
         (not (pair? s)))))

(define leftmost
  (lambda (l)
    (cond
     [(null? l) (quote ())]
     [(atom? (car l)) (car l)]
     [else
      (cond
       [(atom? (leftmost (car l)))
        (leftmost (car l))]
       [else (leftmost (cdr l))])])))

(define leftmost-let
  (lambda (l)
    (cond
     [(null? l) (quote ())]
     [(atom? (car l)) (car l)]
     [else
      (let ([a (leftmost-let (car l))])
        (if (atom? a)
            a
            (leftmost-let (cdr l))))])))

(define leftmost-letcc
  (lambda (l)
    (call/cc
     (lambda (skip)
       (letrec ([L (lambda (l)
                     (cond
                      [(null? l) (quote ())]
                      [(atom? (car l)) (skip (car l))]
                      [else
                       (let ()
                         (L (car l))
                         (L (cdr l)))]))])
         (L l))))))

(leftmost '(((() b)) b))
(leftmost-letcc '(((() b)) b))
(leftmost-let '((((a) b)) b))
(leftmost-letcc '((((a) b)) b))

(define rember1*
  (lambda (a l)
    (letrec
        ([R (lambda (l)
              (cond
               [(null? l) (quote ())]
               [(atom? (car l))
                (if (eq? (car l) a)
                    (cdr l)
                    (cons (car l) (R (cdr l))))]
               [else
                (if (equal? (car l) (R (car l)))
                    (cons (car l) (R (cdr l)))
                    (cons (R (car l)) (cdr l)))]))])
      (R l))))

(define rember1*-let
  (lambda (a l)
    (letrec
        ([R (lambda (l)
              (cond
               [(null? l) (quote ())]
               [(atom? (car l))
                (if (eq? (car l) a)
                    (cdr l)
                    (cons (car l) (R (cdr l))))]
               [else
                (let ([val (R (car l))])
                  (if (equal? val (car l))
                      (cons val (R (cdr l)))
                      (cons val (cdr l))))]))])
      (R l))))

(rember1* 'a '((c d)
               (a b)
               a))
(rember1*-let 'a '((c a d)
                   (a b)
                   a))

(define rm
  (lambda (a l cc)
    (letrec
        ([R (lambda (l cc)
              (cond
               [(null? l) (cc (quote no))]
               [(atom? (car l))
                (if (eq? (car l) a)
                    (cdr l)
                    (cons (car l) (R (cdr l) cc)))]
               [else
                (let ([v (call/cc
                          (lambda (cc)
                            (R (car l) cc)))])
                  (if (atom? v)
                      (cons (car l) (R (cdr l) cc))
                      (cons v (cdr l))))]))])
      (R l cc))))

(define rember1*-letcc
  (lambda (a l)
    (let ([v (call/cc
              (lambda (cc)
                (rm a l cc)))])
      (if (atom? v) l v))))

(rember1*-letcc 'b '((c a d)
                     (a b)
                     a))

(define depth*
  (lambda (l)
    (cond
     [(null? l) 1]
     [(atom? (car l)) (depth* (cdr l))]
     [else (max (add1 (depth* (car l)))
                (depth* (cdr l)))])))

(depth* '(c (b (a b) a) a))
(depth* '(a (a b c (a b))))
