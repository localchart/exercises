#lang racket
(define lens
  (λ (ls)
    (cond
      [(null? ls) 0]
      [else (add1 (lens (cdr ls)))])))

((lambda (len)
   (λ (ls)
     (cond
       [(null? ls) 0]
       [else (add1 ((len len) (cdr ls)))])))
 (lambda (len)
   (λ (ls)
     (cond
       [(null? ls) 0]
       [else (add1 ((len len) (cdr ls)))]))))

((λ (u) (u u))
 (lambda (len)
   (λ (ls)
     (cond
       [(null? ls) 0]
       [else (add1 ((len len) (cdr ls)))]))))

((λ (u) (u u))
 (λ (len)
   (λ (g)
     (λ (ls)
       (cond
         [(null? ls) 0]
         [else (add1 (g (cdr ls)))])))
   (λ (v) ((len len) v))))

;y表达式
(((λ (f)
    ((λ (u) (u u))
     (λ (x)
       (f (λ (v) ((x x) v))))))
  (lambda (len)
    (lambda (ls)
      (cond
        [(null? ls) 0]
        [else (add1 (len (cdr ls)))]))))
 '(a b c))
