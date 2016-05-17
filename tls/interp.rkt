#lang racket
(require racket/match)
(define calc
  (lambda (expr)
    (match expr
      [(? number?) expr]
      [`(,op ,e1 ,e2)
       (let ([v1 (calc e1)]
             [v2 (calc e2)])
         (match op
           ['+ (+ v1 v2)]
           ['- (- v1 v2)]
           ['* (* v1 v2)]
           ['/ (/ v1 v2)]))])))


(define env0 '())

(define ext-env
  (lambda (x v env)
    (cons `(,x . ,v) env)))

(define a (ext-env 'y 8 (ext-env 'x 1 env0)))

(define lookup
  (lambda (x env)
    (let ([p (assq x env)])
      (cond
       [(not p) x]
       [else (cdr p)]))))

(struct Closure (f env))

(define interp1
  (lambda (expr env)
    (match expr      
      [(? symbol? x) (lookup x env)]
      [(? number? x) x]
      [`(lambda (,x) ,e)
       (Closure expr env)]
      [`(,e1 ,e2)
       (let ([v1 (interp1 e1 env)]
             [v2 (interp1 e2 env)])
         (match v1
           [(Closure `(lambda (,x) ,e) env1)
            (interp1 e (ext-env x v2 env1))]))]
      [`(,op ,e1 ,e2)
       (let ([v1 (interp1 e1 env)]
             [v2 (interp1 e2 env)])
         (match op
           ['+ (+ v1 v2)]
           ['- (- v1 v2)]
           ['* (* v1 v2)]
           ['/ (/ v1 v2)]))])))

(define interp
  (lambda (expr)
    (interp1 expr env0)))

;(interp '(+ 1 3))

;(interp '(* (+ 1 2) (+ 3 4)))

;(interp '((lambda (x) (* 2 x)) 3))
;(interp '(((lambda (x) (lambda (y) (* x y))) 2) 3))

(define t 
  (lambda (a)
    (lambda (b)
      `(,b ,a))))










