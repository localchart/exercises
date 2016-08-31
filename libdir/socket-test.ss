(import (socket))

(define echo-server-socket (make-server-socket "5000"))
(define stack '())

(define (server-run)
  (rec accept
       (lambda ()
         (call-with-socket
          (socket-accept echo-server-socket)
          proc)
         (accept))))))

(define (get-line-from-binary-port bin)
  (utf8->string
   (call-with-bytevector-output-port
    (lambda (out)
      (let loop ((b (get-u8 bin)))
        (case b
          ((#xA) #t) ;; newline
          ((#xD) (loop (get-u8 bin))) ;; carriage return
          ((eof-object?) #f) ;; eof
          (else (put-u8 out b) (loop (get-u8 bin)))))))))

(define proc
  (lambda (sock)
  	(set! stack (cons sock stack))))

(fork-thread
 (server-run))
(fork-thread
 (server-run))


(import (socket))
;; simple echo client
(define client-socket
  (make-client-socket "localhost" "5000"
                      (address-family inet)
                      (socket-domain stream)
                      (address-info v4mapped addrconfig)
                      (ip-protocol ip)))
(socket-send client-socket (string->utf8 "hello\r\n"))


(socket-recv client-socket (string-length "hello\r\n"))
