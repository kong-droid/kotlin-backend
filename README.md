kotlin-backend
===

### 1. spec
```
- language: 
    - java 11 (jvm 1.6.21)
    - kotlin 1.6.21
- framework:
    - spring boot 2.7.17
- dbms:
    - postgresql: 14.5
```

### 2. package
```
- site.kongdroid.kotlinbackend
    - controller
    - service
    - repository
    - domain
        - request
        - response
        - entity
    - Application.kt
```

### 3. caution
```
- java 11은 spring boot 2.7.X 버전까지만 지원한다. 3.X 부터 지원하지 않는다.
- 로컬 서버가 설정되어 있어야한다.
```