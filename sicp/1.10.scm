(define (A x y)
  (cond ((= y 0) 0)
        ((= x 0) (* 2 y))
        ((= y 1) 2)
        (else (A (- x 1)
                 (A x (- y 1))))))

(define (count-change amount)
  (cc amount 5))

(define (cc amount kind)
  (cond ((= amount 0) 1)
        ((or (< amount 0)
             (= kind 0)) 0)
        (else (+
                (cc (- amount (coins kind)) kind)
                (cc amount (- kind 1))))))

(define (coins kind-cins)
  (cond ((= kind-cins 1) 1)
        ((= kind-cins 2) 5)
        ((= kind-cins 3) 10)
        ((= kind-cins 4) 25)
        ((= kind-cins 5) 50)))
