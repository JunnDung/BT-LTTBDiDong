# BT-T1
Bài tập tuần 1 môn Lập trình thiết bị di động
1. Mong muốn và định hướng của bạn là gì sau khi học xong môn học?
Sau khi hoàn thành môn học, em mong muốn có thể tự mình tạo ra một ứng dụng di động hoàn chỉnh bằng Kotlin. Em muốn hiểu rõ cách xây dựng giao diện người dùng (UI), xử lý dữ liệu, tích hợp API và tối ưu hiệu suất ứng dụng. Định hướng lâu dài của em là có thể phát triển các ứng dụng di động thực tế, phục vụ cho công việc hoặc kinh doanh trong tương lai.

2. Theo bạn, trong tương lai gần (10 năm) lập trình di động có phát triển không? Giải thích tại sao?
Em tin rằng lập trình di động sẽ tiếp tục phát triển mạnh trong 10 năm tới vì các lý do sau:
- Sự phổ biến của điện thoại thông minh: Ngày càng nhiều người sử dụng smartphone để thực hiện công việc, giải trí và giao tiếp.
- Nhu cầu ứng dụng ngày càng cao: Doanh nghiệp và cá nhân đều cần các ứng dụng để phục vụ nhu cầu mua sắm, học tập, làm việc, y tế, tài chính,...
- Công nghệ mới ra đời: Các công nghệ như AI, IoT, AR/VR sẽ tích hợp sâu hơn vào ứng dụng di động, mở ra nhiều cơ hội mới.
- Hệ sinh thái ứng dụng mở rộng: Các nền tảng như Android và iOS không ngừng cải tiến, cung cấp nhiều công cụ hỗ trợ lập trình viên phát triển ứng dụng nhanh và hiệu quả hơn.
3. Viết một ứng dụng có UI như hình và đẩy lên GitHub
    Mô tả bài tập:
- Tạo một màn hình profile đơn giản sử dụng Jetpack Compose
- Gồm các thành phần: header với nút back/edit, ảnh đại diện, tên và địa chỉ
- Tất cả được căn giữa và có layout hợp lý
    Mục tiêu:
- Học cách sử dụng Jetpack Compose để tạo UI
- Thực hành layout với Box, Column, Row
- Hiểu cách sắp xếp các thành phần UI
    Kết quả đạt được:
- Tạo được UI giống mẫu
- Code sạch, có cấu trúc
    Giải thích các hàm chính:
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Hàm khởi tạo activity, setup content với Compose
    }
}

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        // Box: Container chính, cho phép xếp chồng các phần tử
        // Header (Row): Chứa 2 icon ở trên cùng
        // Content chính (Column): Chứa avatar, tên và địa chỉ
    }
}

// Các thành phần UI chính:
Row {
    // Sắp xếp các phần tử theo chiều ngang
    // horizontalArrangement = Arrangement.SpaceBetween: Tạo khoảng cách giữa các phần tử
}

Column {
    // Sắp xếp các phần tử theo chiều dọc
    // horizontalAlignment = Alignment.CenterHorizontally: Căn giữa theo chiều ngang
    // verticalArrangement = Arrangement.Center: Căn giữa theo chiều dọc
}

Image {
    // Hiển thị ảnh đại diện
    // modifier = Modifier.clip(CircleShape): Cắt ảnh thành hình tròn
}

Text {
    // Hiển thị text với style từ MaterialTheme
}
