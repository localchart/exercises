(define-record fitem (result ready mutex cond))
 
(define future
  (lambda (thunk)
    (let ([item (make-fitem #f #f (make-mutex) (make-condition))])
      (fork-thread
       (lambda ()
         (let ([result (thunk)])
           (with-mutex (fitem-mutex item)
             (set-fitem-result! item result)
             (set-fitem-ready! item #t)
             (condition-broadcast (fitem-cond item))))))
      item)))
 
 
(define touch
  (lambda (item)
    (let ([mutex (fitem-mutex item)])
      (with-mutex mutex
        (let loop ()
          (when (not (fitem-ready item))
            (condition-wait (fitem-cond item) mutex)
            (loop)))
        (fitem-result item)))))