#lang racket
(define (atom? x) (not (pair? x)))
(define insertR*
  (λ (new old lat)
    (cond
      [(null? lat) '()]
      [(atom? (car lat))
       (if (eq? (car lat) old)
           (cons old (cons new (insertR* new old (cdr lat))))
           (cons (car lat) (insertR* new old (cdr lat))))]
      [else (cons (insertR* new old (car lat))
                  (insertR* new old (cdr lat)))])))
(insertR* 'roast 'chuck '((how much (wood))
                           could
                           ((a (wood) chuck))
                           (((chuck)))
                           (if (a) ((wood chuck)))
                           could chuck wood))