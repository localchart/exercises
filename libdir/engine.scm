;;;
;;;  Engine
;;;

(define clock #f)

(define handler #f)

(define start-timer
  (lambda (ticks new-handler)
    (set! handler new-handler)
    (set! clock ticks)))

(define stop-timer
  (lambda ()
    (let ((time-left clock))
      (set! clock 0)
      time-left)))

(define decrement-timer
  (lambda ()
    (when (> clock 0)
        (begin
          (set! clock (- clock 1))
          (when (= clock 0) (handler))))))

(define make-engine
  (let ((do-complete #f)
        (do-expire #f))
    (define timer-handler
      (lambda ()
        (start-timer (call/cc do-expire) timer-handler)))
    (define new-engine
      (lambda (resume)
        (lambda (ticks complete expire)
          ((call/cc
            (lambda (escape)
              (set! do-complete
                    (lambda (ticks val)
                      (escape (lambda () (complete ticks val)))))
              (set! do-expire
                    (lambda (resume)
                      (escape (lambda () (expire (new-engine resume)))))))