(define expand-only
  (lambda (kwds expr)
    (caddr
      (expand
        `(lambda ,(remp (lambda (x) (memq x kwds)) (oblist))
           ,expr)))))