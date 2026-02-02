## Polymorphism (đa hình) trong OOP là gì?
Đa hình là khả năng cùng một tên hàm / phương thức nhưng có thể có nhiều cách hành xử khác nhau, tùy vào ngữ cảnh.

👉 Overloading (nạp chồng)
- Cùng tên hàm
- Khác danh sách tham số (só lượng, kiểu, thứ tự)
- Xảy ra tại thời điểm biên dịch - compile time

 👉 Overriding (ghi đè)
 - Xảy ra trong kế thừa
 - Lớp con định nghĩa lại phương thức của lớp cha
 - Quyết định khi chạy chương trình - runtime

| Đặc điểm             | C++               | Java         | Python  |
| :--------------------: | :-----------------: | :------------: | :-------: |
| Overloading          | ✔                 | ✔            | ❌       |
| Overriding mặc định  | ❌ (cần `virtual`) | ✔            | ✔       |
| Runtime polymorphism | ✔                 | ✔            | ✔       |
| Duck typing          | ❌                 | ❌            | ✔       |
| Kiểm tra kiểu        | Compile-time      | Compile-time | Runtime |

### 1. Binding là gì?
Binding là quá trình liên kết một lời gọi hàm với phần thân hàm sẽ được thực thi.

#### 1.1. Static Binding (Early Binding)
⏰ Quyết định khi compile

🔹 Đặc điểm
- Dựa vào kiểu biến
- Nhanh hơn
- Không có polymorphism runtime

🔹 Xảy ra khi:
- Overloading
- static, final, private (Java)
- Hàm không virtual (C++)

```java
class Animal {
    static void speak() {
        System.out.println("Animal");
    }
}

class Dog extends Animal {
    static void speak() {
        System.out.println("Dog");
    }
}
Animal a = new Dog();
a.speak();   // Animal ❗

```

#### 1.2. Dynamic Binding (Late Binding)

⏰ Quyết định khi runtime

🔹 Đặc điểm

- Dựa vào object thực tế
- Là nền tảng của polymorphism
- Chậm hơn chút nhưng linh hoạt

🔹 Điều kiện

- Có kế thừa
- Có overriding
- Gọi qua tham chiếu lớp cha

```java
class Animal {
    void speak() {
        System.out.println("Animal");
    }
}

class Dog extends Animal {
    @Override
    void speak() {
        System.out.println("Dog");
    }
}

Animal a = new Dog();
a.speak();   // Dog ✅

```

### 2. Phân tích từ khóa "virtual", và lý do chỉ có trong C++ mới sử dụng

Trong C++ OOP, từ khóa `virtual` dùng để hỗ trợ đa hình (polymorphism), cụ thể hơn là 
hàm nào được gọi sẽ phụ thuộc vào đối tượng thật mà con trỏ đang trỏ tới, chứ không phải kiểu của con trỏ

Cho ví dụ: ta có 2 class Animal và Dog, trong đó class Dog kế thừa từ Animal, cả 2 cùng có phương thức `speak()`. Xét chung đoạn code trong hàm `main()` là :

```
Animal* a = new Dog();
a->speak();
```

- Trường hợp 1: phương thức `speak()` của class Animal không được khai báo virtual, và class Dog cũng không override phương thức này
➡️ Khi gọi `speak()` thì `speak()` của Animal sẽ được gọi
- Trường hợp 2: phương thức `speak()` của class Animal được khai báo virtual và bên trong class Dog phương thức này được override
➡️ Khi gọi `speak()` thì `speak()` của Dog sẽ được gọi

📌 Nguyên nhân của vấn đề này là vì C++ quyết định hàm ngay lúc biên dịch (compile time), trong C++ có bảng hàm ảo (vtable) cho mỗi class; khi gọi hàm `virtual`, C++ tra vtable 👉 gọi đúng hàm

💡`virtual destructor` rất quan trọng trong C++ OOP, nếu xóa object qua con trỏ base class mà destructor không virtual ➡️ memory leak / undefined behavior
