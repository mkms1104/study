FROM node

# 컨테이너의 작업 디렉토리를 설정한다.
WORKDIR /app

# Docker 파일이 위치한 로컬 경로의 파일을 컨테이너 내부의 /app 경로에 복사한다.
# 컨테이너 내부에 /app 경로가 존재하지 않으면 생성된다.
# WORKDIR을 설정하지 않았다면 /app 경로를 직접 지정해야 한다.
# COPY . /app
COPY . .

# 도커 컨테이너 및 이미지의 작업 디렉토리에서 실행된다. (default는 컨테이너 파일 시스템의 루트 경로)
RUN npm install

# 컨테이너 외부에 노출할 포트를 지정한다.
EXPOSE 80

# 이미지가 생성될 때 실행되지 않고 이미지를 기반으로 컨테이너가 시작될 때 실행된다.
CMD ["node", "server.js"]