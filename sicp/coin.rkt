#lang racket
(define find
  (λ (n r) 
    (find-help n r '())))

(define find-help
  (λ (n sum ret)
    (let ([next (sub1 n)]
          [left (- sum n)]
          [total (apply + n (range n))])
      (cond
        [(>= n sum) (find-help (sub1 sum) sum (cons (list sum) ret))]
        [(< total sum) ret]
        [else (find-help next sum 
                         (append ret (map (lambda (x) (cons n x))
                                          (find-help next left '()))))]))))

(define (findn n sum) 
  (cond ((= 0 sum) (list '())) 
        ((< sum 0) '()) 
        ((<= n 0) '()) 
        (else (append 
               (findn (- n 1) sum) 
               (map (lambda (x) (cons n x)) 
                    (findn (- n 1) (- sum n))))))) 