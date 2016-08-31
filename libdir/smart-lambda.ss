; Originally Written by Aziz Ghuloum
; Modified by Andy Keep for R6 compatibility
; 
; really good for finding errors in programs when using petite
;
; Example:
; (define foo
;  (lambda (x)
;   (+ x x)))
;(foo 3 4) => exception in apply: incorrect args (3 4) for (lambda (x) (+ x x))

(library (smart-lambda)
  (export lambda)
  (import (except (chezscheme) lambda))

  (define-syntax lambda
    (let ()
      (import (chezscheme))
      (syntax-rules ()
        [(_ args b b* ...)
          (case-lambda
            [args b b* ...]
            [others (errorf 'apply "incorrect args ~s for ~s"
                      others '(lambda args b b* ...))])]))))

(import (smart-lambda))