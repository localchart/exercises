(library (abc)
  (export $socket $getaddrinfo $getprotobyname $getprotobynumber $bind $listen $accept
          $connect $shutdown $sendto $recvfrom $getsockopt $setsockopt  $close  $fcntl init)
  (import (chezscheme))
  (define init
    (begin
      (load-shared-object "ws2_32.dll")
      (load-shared-object "crtdll.dll")
      (load-shared-object "kernel32.dll")))

  (define $socket
    (foreign-procedure __stdcall "socket" (fixnum fixnum fixnum) fixnum))
  (define $getaddrinfo
    (foreign-procedure __stdcall "getaddrinfo" (string string uptr uptr) int))
  (define $getprotobyname
    (foreign-procedure __stdcall "getprotobyname" (string) uptr))
  (define $getprotobynumber
    (foreign-procedure __stdcall "getprotobynumber" (fixnum) uptr))
  (define $bind
    (foreign-procedure __stdcall "bind" (fixnum uptr fixnum) fixnum))
  (define $listen
    (foreign-procedure __stdcall "listen" (fixnum fixnum) fixnum))
  (define $accept
    (foreign-procedure __stdcall "accept" (fixnum uptr uptr) fixnum))
  (define $connect
    (foreign-procedure __stdcall "connect" (fixnum uptr fixnum) fixnum))
  (define $shutdown
    (foreign-procedure __stdcall "shutdown" (fixnum fixnum) fixnum))
  (define $sendto
    (foreign-procedure __stdcall "sendto"
                       (fixnum u8* fixnum fixnum uptr fixnum)
                       fixnum))
  (define $recvfrom
    (foreign-procedure __stdcall "recvfrom"
                       (fixnum u8* fixnum fixnum uptr uptr)
                       fixnum))
  (define $getsockopt
    (foreign-procedure __stdcall "getsockopt" (int int int uptr uptr) int))
  (define $setsockopt
    (foreign-procedure __stdcall "setsockopt" (int int int uptr int) int))

  (define $close
    (foreign-procedure __stdcall "closesocket" (unsigned) int))
  (define $fcntl
    (foreign-procedure __stdcall "ioctlsocket" (unsigned unsigned unsigned) int))
  )
