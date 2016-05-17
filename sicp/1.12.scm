(define (f row col)
  (if (or (= col 1)
          (= row col))
    1
    (+ (f (- row 1) (- col 1))
       (f (- row 1) col))))

(define (ff row col)
  (cond ((or (< row col)
             (<= row 0)
             (<= col 0)) 0)
        ((or (= col 1)
             (= row col)) 1)
        (else (+ (ff (- row 1)
                     (- col 1))
                 (ff (- row 1) col)))))