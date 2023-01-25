# bulletin-board

## 📑목차

---
1. [프로젝트 소개](#🎤️프로젝트-소개)
2. [프로젝트 사용 링크](#📲프로젝트-사용-링크)
3. [버전 기획](#✨버전)
4. [진행 과정](#💻진행-과정)
5. [저작권, 라이센스](#🪪저작권-라이센스)
6. [외부 리소스](#🧬외부-리소스)


## 🎤️프로젝트 소개

---
- 기본적인 인터넷 커뮤니티 게시판의 서비스 기능을 지향하며, 백엔드 전반의 기술을 익히기 위해서 만든 게시판 입니다.

## 📲프로젝트 사용 링크

---
http://ec2-3-34-59-239.ap-northeast-2.compute.amazonaws.com/

## ✨버전

---
### version 1.0
- 글쓰기,글게시
- 회원가입
- 로그인,로그아웃
- 소셜 로그인 (구글, 네이버)

## 💻진행 과정

---
- 서블릿을 이용한 폼 로그인, 회원가입, 글쓰기, 글수정 기능 구현
- Spring Data JPA로 마이그레이션
- Spring Security로 소셜 로그인, 폼 로그인 구현
- AWS EC2 사용하여 Linux 서버 구축
- AWS RDS 사용하여 데이터베이스(MariaDB) 환경 구축
- EC2, RDS 연동 및 게시판 프로젝트 수동 배포
- Travis CI & AWS S3 & AWS CodeDeploy 등을 연동하여 Github에 코드를 push하면 자동으로 배포 자동화 구성
- Nginx툴을 이용하여 무중단 배포 구현

## 🪪저작권 라이센스

---
- Language: Java
- Framework: Spring Boot
- Storage: AWS S3
- Server: AWS EC2
- ORM skill: Spring Data JPA
- Database: AWS RDS(MariaDB) 
- CI tool: Travis CI
- CD tool: AWS CodeDeploy, Nginx
- Social Login: Security OAuth 2.0
- Form Login: Security Form Login, Java Development Login
- etc: Lombok

## 🧬외부 리소스
https://github.com/jojoldu/freelec-springboot2-webservice