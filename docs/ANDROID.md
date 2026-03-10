# Android App Documentation

## Overview

The Chamaa Android app is built with Kotlin and Jetpack Compose, providing a mobile interface for the community lending platform.

## Technology Stack

| Component | Technology |
|-----------|------------|
| Language | Kotlin |
| UI Framework | Jetpack Compose |
| Architecture | MVVM |
| Networking | Retrofit 2 + OkHttp |
| JSON Parsing | Gson |
| Local Storage | SharedPreferences (tokens), Room (planned) |
| DI | Manual (planned: Hilt) |
| Min SDK | 26 (Android 8.0) |
| Target SDK | 35 |

## Project Structure

```
apps/android/ChamaApp/
├── app/
│   ├── src/main/
│   │   ├── java/com/example/chama/
│   │   │   ├── MainActivity.kt          # Entry point
│   │   │   ├── network/                  # Networking layer
│   │   │   │   ├── RetrofitInstance.kt  # Retrofit singleton
│   │   │   │   ├── ApiService.kt        # API interface
│   │   │   │   ├── AuthInterceptor.kt   # Auth token handler
│   │   │   │   ├── TokenManager.kt      # Token storage
│   │   │   │   └── ErrorHandler.kt      # Error handling
│   │   │   ├── models/                  # Data models
│   │   │   │   ├── BaseResponse.kt      # Generic response wrapper
│   │   │   │   ├── UserModel.kt
│   │   │   │   ├── GroupModel.kt
│   │   │   │   └── LoanModel.kt
│   │   │   └── ui/                      # UI components
│   │   │       └── theme/
│   │   └── res/                         # Resources
│   └── build.gradle.kts                 # Dependencies
├── gradle/
└── build.gradle.kts
```

## Network Layer

### RetrofitInstance

Singleton Retrofit instance with OkHttp client:

```kotlin
object RetrofitInstance {
    private const val BASE_URL = "http://10.0.2.2:8080/api/"
    
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor())
        .addInterceptor(HttpLoggingInterceptor())
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()
    
    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .create(ApiService::class.java)
    }
}
```

### ApiService

REST API interface:

```kotlin
interface ApiService {
    // Users
    @POST("users")
    suspend fun createUser(@Body user: UserModel): Response<BaseResponse<UserModel>>
    
    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: Long): Response<BaseResponse<UserModel>>
    
    // Groups
    @POST("groups")
    suspend fun createGroup(@Body group: GroupModel): Response<BaseResponse<GroupModel>>
    
    @GET("groups")
    suspend fun getGroups(): Response<BaseResponse<List<GroupModel>>>
    
    // Loans
    @POST("loans")
    suspend fun requestLoan(@Body loan: LoanModel): Response<BaseResponse<LoanModel>>
    
    @GET("loans/{id}")
    suspend fun getLoan(@Path("id") id: Long): Response<BaseResponse<LoanModel>>
}
```

### AuthInterceptor

Attaches Firebase ID token to requests:

```kotlin
class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val token = TokenManager.getToken()
        
        val newRequest = request.newBuilder()
            .header("Authorization", "Bearer $token")
            .header("Content-Type", "application/json")
            .build()
        
        return chain.proceed(newRequest)
    }
}
```

### TokenManager

Manages authentication tokens using SharedPreferences:

```kotlin
object TokenManager {
    private const val PREF_NAME = "chamaa_prefs"
    private const val KEY_TOKEN = "auth_token"
    
    fun init(context: Context) { ... }
    fun saveToken(token: String) { ... }
    fun getToken(): String? { ... }
    fun clearToken() { ... }
}
```

### ErrorHandler

Standardized error handling:

```kotlin
object ErrorHandler {
    fun <T> handleResponse(response: Response<T>): Result<T> {
        return when {
            response.isSuccessful -> { ... }
            response.code() == 401 -> { ... }  // Clear token, prompt re-login
            response.code() == 404 -> { ... }
            response.code() == 500 -> { ... }
            else -> { ... }
        }
    }
}
```

## Data Models

### BaseResponse

Generic response wrapper for all API calls:

```kotlin
data class BaseResponse<T>(
    val success: Boolean,
    val message: String,
    val data: T? = null,
    val error: String? = null
)
```

### UserModel

```kotlin
data class UserModel(
    val id: Long? = null,
    val email: String,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String
)
```

### GroupModel

```kotlin
data class GroupModel(
    val id: Long? = null,
    val name: String,
    val description: String,
    val creatorId: Long,
    val targetAmount: Double
)
```

### LoanModel

```kotlin
data class LoanModel(
    val id: Long? = null,
    val borrowerId: Long,
    val groupId: Long,
    val amount: Double,
    val durationInMonths: Int,
    val purpose: String
)
```

## Setup

### Prerequisites

- Android Studio Ladybug+
- JDK 11+
- Android SDK 35

### Build

```bash
cd apps/android/ChamaApp
./gradlew assembleDebug
```

### Install on Emulator

```bash
./gradlew installDebug
```

### Connect to Backend

The app connects to `http://10.0.2.2:8080/api/` - this is the Android emulator's alias for the host machine's localhost.

For physical device testing, use your machine's local IP address.

## Usage

### Initialize TokenManager

In `MainActivity.kt`:

```kotlin
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TokenManager.init(this)
        // ... rest of setup
    }
}
```

### Making API Calls

```kotlin
// Example: Fetch groups
suspend fun loadGroups() {
    try {
        val response = RetrofitInstance.api.getGroups()
        val result = ErrorHandler.handleResponse(response)
        
        result.onSuccess { baseResponse ->
            val groups = baseResponse.data
            // Update UI
        }.onFailure { error ->
            // Show error
        }
    } catch (e: Exception) {
        // Handle network error
    }
}
```

## Dependencies

```kotlin
// Retrofit
implementation("com.squareup.retrofit2:retrofit:2.9.0")
implementation("com.squareup.retrofit2:converter-gson:2.9.0")

// OkHttp
implementation("com.squareup.okhttp3:okhttp:4.12.0")
implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

// Coroutines
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

// Room (planned)
implementation("androidx.room:room-runtime:2.6.1")
implementation("androidx.room:room-ktx:2.6.1")
```

## Future Enhancements

- [ ] Hilt dependency injection
- [ ] Room database for offline support
- [ ] Firebase Authentication
- [ ] Push notifications
- [ ] Image upload for profiles
- [ ] Biometric authentication
- [ ] Dark theme support
