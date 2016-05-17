(define (f n)
  (cond ((< n 3) n)
        (else (+ (f (- n 1))
                 (* 2 (f (- n 2)))
                 (* 3 (f (- n 3)))))))

(define (ff n)
  (define (iter a b c n)
    (if (= n 2)
      c
      (iter b c (+ c (* 2 b) (* 3 a)) (- n 1))))
  (if (< n 3)
    n
    (iter 0 1 2 n)))
