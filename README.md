# 🏢 Apartix - Apartman ve Site Yönetim Sistemi

Apartix, apartman ve site yönetim süreçlerini dijital ortama taşımak amacıyla geliştirilmiş web tabanlı bir yönetim platformudur.

Sistem sayesinde yöneticiler ve sakinler tek bir platform üzerinden aidat, duyuru ve arıza işlemlerini yönetebilir.

---

## ✨ Özellikler

### Yönetici Paneli

* Site oluşturma
* Katılım kodu oluşturma
* Sakin kayıtlarını görüntüleme
* Aidat takibi
* Arıza kayıtlarını görüntüleme ve yönetme
* Duyuru oluşturma
* Site istatistiklerini görüntüleme

### Sakin Paneli

* Katılım kodu ile siteye kayıt olma
* Aidat durumunu görüntüleme
* Aidat ödeme işlemleri
* Arıza bildirimi oluşturma
* Duyuruları görüntüleme
* Şifre değiştirme

### Güvenlik

* Rol bazlı giriş sistemi
* Yönetici / Sakin ayrımı
* Aynı daireye çift kayıt engeli
* Siteye katılım kodu doğrulaması

---

## 🛠 Kullanılan Teknolojiler

### Backend

* Java
* Spring Boot
* Spring Data JPA
* Maven
* MySQL

### Frontend

* HTML5
* CSS3
* JavaScript
* Bootstrap 5
* Chart.js

### Veritabanı

* MySQL

---

# 🚀 Kurulum

## 1. Projeyi Klonlayın

```bash
git clone https://github.com/karadag07/apartix.git
```

## 2. MySQL Veritabanını Oluşturun

MySQL üzerinde yeni bir veritabanı oluşturun:

```sql
CREATE DATABASE apartman_db;
```

Daha sonra proje içerisindeki SQL dosyasını içe aktarın.

---

## 3. application.properties Ayarları

`src/main/resources/application.properties`

dosyasındaki veritabanı bilgilerini kendi sisteminize göre düzenleyin.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/apartman_db
spring.datasource.username=root
spring.datasource.password=ŞİFRENİZ

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## 4. Backend'i Çalıştırın

Terminal üzerinden:

```bash
mvn spring-boot:run
```

veya

IDE üzerinden:

```text
DemoApplication.java
↓
Run
```

Backend varsayılan olarak:

```text
http://localhost:8080
```

adresinde çalışacaktır.

---

## 5. Frontend'i Açın

Proje dizinindeki:

```text
Apartix.html
```

dosyasını tarayıcıda açın.

veya VS Code Live Server kullanabilirsiniz.

---

# 👥 Kullanıcı Rolleri

## Yönetici

* Site oluşturabilir
* Katılım kodu oluşturabilir
* Aidatları takip edebilir
* Arıza kayıtlarını yönetebilir
* Duyuru paylaşabilir

## Sakin

* Katılım kodu ile kayıt olabilir
* Aidatlarını görüntüleyebilir
* Arıza bildirimi oluşturabilir
* Duyuruları görüntüleyebilir

---

# 📌 Proje Notları

* Bu proje eğitim amaçlı geliştirilmiştir.
* Aidat ödeme sistemi simülasyon mantığıyla çalışmaktadır.
* Şifreler düz metin olarak saklanmaktadır. Gerçek sistemlerde BCrypt gibi yöntemlerle şifrelenmelidir.

#
