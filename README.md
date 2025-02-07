# 📌 Medical App (Jetpack Compose)

## 🚀 Overview
A modern **medical management app** built with **Jetpack Compose** using the **MVI architecture**. This app supports **task management, reports, attendance tracking, and user authentication.**

## 🏗 Tech Stack
- **Kotlin** (Jetpack Compose)
- **MVI Architecture** (Model-View-Intent)
- **Navigation Component** (Compose Navigation)
- **Retrofit** (API calls)
- **ViewModel & State Management**
- **Room Database** (Optional, for offline storage)
- **Coroutine** 

## 🛠 Features
### ✅ Authentication & Profile
- [ ] **Login Screen** (`POST /login`)
- [ ] **Register Screen** (`POST /register`)
- [ ] **Profile Screen** (`GET /show-profile`)
- [ ] **PreferenceManager** (User Session Storage)

### ✅ Task Management
- [ ] **Tasks List Screen** (`GET /tasks`)
- [ ] **Create Task Screen** (`POST /create-task`)
- [ ] **Employee Dropdown (Manager, Doctor, Receptionist, HR, Nurse)`**
- [ ] **Task Assignment & Selection**

### ✅ Reports & Attendance
- [ ] **Reports List Screen** (`GET /reports`)
- [ ] **Create Report Screen** (`POST /create-report`)
- [ ] **Upload Image (Multipart API)`**
- [ ] **Attendance Tracking (Check-in, Check-out)**

### ✅ UI/UX Improvements
- [ ] **Dark Mode Support**
- [ ] **Animations & Transitions**
- [ ] **Accessibility Improvements**

## 📌 Project Setup
```sh
git clone <your-github-repo-url>
cd medical-app
./gradlew build
./gradlew run
```

## 📍 GitHub Workflow
### Branching Strategy
- `main` → Stable Release
- `dev` → Active Development
- `feature/XYZ` → Specific Feature

### Git Commands
```sh
git init
git add .
git commit -m "Initial commit"
git branch -M main
git remote add origin <your-github-repo-url>
git push -u origin main
```

## 📢 Contributing
- Fork the repository
- Create a feature branch (`git checkout -b feature-name`)
- Commit your changes (`git commit -m "Added feature"`)
- Push to GitHub (`git push origin feature-name`)
- Create a Pull Request

## 📄 License
MIT License © 2025
