#lang racket

(define env0 '())

(define ext
  (lambda (k v env)
    (cons (cons k v) env)))

(define lookup
  (lambda (k env)
    (cond
     [(assq k env) => cdr]
     [else #f])))

(struct closure (fun env))

(struct thunk (fv cached?) #:transparent #:mutable)

(define make-thunk
  (lambda (fun)
    (thunk fun #f)))

(define force-thunk
  (lambda (th)
    (cond
     [(thunk-cached? th)
      (thunk-fv th)]
     [else
      (let loop ([fv ((thunk-fv th))])
        (cond
         [(thunk? fv)
          (loop ((thunk-fv fv)))]
         [else
          (set-thunk-fv! th fv)
          (set-thunk-cached?! th #t)
          fv]))])))

(define interp1
  (lambda (exp env)
    (match exp
      [(? number? x) x]
      [(? symbol? x) (lookup x env)]
      [`(lambda (,x) ,e)
       (closure exp env)]
      [`(,e1 ,e2)
       (let ([v1 (make-thunk (lambda () (interp1 e1 env)))]
             [v2 (make-thunk (lambda () (interp1 e2 env)))])
         (make-thunk
          (lambda ()
            (let ([v1+ (force-thunk v1)])
              (match v1+
                [(closure `(lambda (,x) ,e) env1)
                 (interp1 e (ext x v2 env1))])))))])))

(define interp
  (lambda (exp)
    (force-thunk (interp1 exp env0))))

(interp
 '((lambda (x) 42)
   ((lambda (x) (x x))
    (lambda (x) (x x)))))
