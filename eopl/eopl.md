# Essentials of Programming Languages Study Notes
Some notes and exercises for study.

[TOC]

## Chapter 1

```Lisp
;; in-S? : N -> bool
;; usage: (in-S? n) = #t if n is in S, #f otherwise
(define in-S?
  (lambda (n)
    (cond
     [(zero? n) #t]
     [(positive? (- n 3)) (in-S? (- n 3))]
     [else #f])))
```
### Exercise 1.1

* Top-Down
* Bottom-Up
* Rules of inference
  $$
  \frac{}{2 \in N}\\\frac{n \in N}{(n + 3) \in N}
  $$







