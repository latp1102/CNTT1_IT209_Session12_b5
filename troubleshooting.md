# Session12 - Bài 5: Chẩn đoán và khắc phục sự cố Pipeline CI/CD

## 1. Mục tiêu

Thực hành nhận diện và khắc phục ba lỗi thường gặp trong GitHub Actions Pipeline: Gradle Wrapper không có quyền thực thi, xác thực GitHub Container Registry thất bại và Docker Build Context không chính xác.

## 2. Các sự cố và cách khắc phục

### Lỗi 1: Permission denied khi chạy Gradle Wrapper

**Nguyên nhân:** Tệp `gradlew` chưa có quyền thực thi trên môi trường Linux.

**Cách khắc phục:**

```bash
chmod +x gradlew
```

Sau đó chạy kiểm thử:

```bash
./gradlew test
```

### Lỗi 2: Authentication Failed khi đăng nhập GHCR

**Nguyên nhân:** Tên GitHub Actions Secret trong workflow không khớp với tên Secret được cấu hình trong repository.

**Cách khắc phục:** Sử dụng `secrets.REGISTRY_TOKEN` và tạo Repository Secret cùng tên trong Settings → Secrets and variables → Actions. Secret chứa GitHub Personal Access Token có quyền phù hợp để đẩy package.

### Lỗi 3: Docker Build Context Error

**Nguyên nhân:** Workflow tham chiếu đến `./app` và `./app/Dockerfile`, trong khi Dockerfile và `index.html` nằm ở thư mục gốc repository.

**Cách khắc phục:**

```yaml
context: .
file: ./Dockerfile
```

## 3. Kết quả

Sau khi sửa các lỗi, job `Gradle Test` và job `Build and Push Docker Image` đều hoàn thành thành công trên GitHub Actions. Docker image được cấu hình để đẩy lên GitHub Container Registry (GHCR).

## 4. Kết luận

Bài thực hành giúp hiểu cách chẩn đoán lỗi quyền thực thi, quản lý GitHub Actions Secrets và cấu hình Docker Build Context. Việc kiểm tra log từng job giúp xác định nguyên nhân và khắc phục sự cố CI/CD có hệ thống.
