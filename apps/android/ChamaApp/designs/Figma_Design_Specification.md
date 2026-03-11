# ChamaApp Design System - Figma Implementation Guide

## Design System Overview

### Brand Colors
| Name | Hex Code | Usage |
|------|----------|-------|
| Primary | #1A237E | Headers, buttons, accents |
| Primary Light | #3949AB | Hover states, secondary buttons |
| Primary Dark | #0D1442 | Text on light backgrounds |
| Secondary | #3949AB | Alternative actions |
| Accent | #FFD700 | Highlights, badges, achievements |
| Background Start | #E3F2FD | Screen backgrounds (top) |
| Background Mid | #FFFFFF | Screen backgrounds (center) |
| Background End | #F5F5F5 | Screen backgrounds (bottom) |
| Text Primary | #1A237E | Headings, important text |
| Text Secondary | #666666 | Body text, descriptions |
| Text Tertiary | #888888 | Captions, hints |
| Success | #4CAF50 | Success states, positive values |
| Warning | #FF9800 | Warnings, pending states |
| Error | #F44336 | Errors, negative values |
| White | #FFFFFF | Cards, input backgrounds |
| Divider | #E0E0E0 | Lines, separators |

### Typography
| Style | Font | Size | Weight | Line Height |
|-------|------|------|--------|-------------|
| Display | System (Sans-Serif) | 36sp | Bold (700) | 44sp |
| H1 | System (Sans-Serif) | 28sp | Bold (700) | 36sp |
| H2 | System (Sans-Serif) | 24sp | SemiBold (600) | 32sp |
| H3 | System (Sans-Serif) | 20sp | SemiBold (600) | 28sp |
| Body Large | System (Sans-Serif) | 16sp | Regular (400) | 24sp |
| Body | System (Sans-Serif) | 14sp | Regular (400) | 20sp |
| Caption | System (Sans-Serif) | 12sp | Regular (400) | 16sp |
| Button | System (Sans-Serif) | 16sp | Bold (700) | 24sp |

### Spacing System (8pt Grid)
| Token | Value |
|-------|-------|
| xs | 4px |
| sm | 8px |
| md | 16px |
| lg | 24px |
| xl | 32px |
| xxl | 48px |

### Border Radius
| Token | Value |
|-------|-------|
| Small | 8px |
| Medium | 12px |
| Large | 16px |
| Full | 9999px (pill) |

### Shadows
| Level | Values |
|-------|--------|
| Low | 0 2px 4px rgba(0,0,0,0.08) |
| Medium | 0 4px 8px rgba(0,0,0,0.12) |
| High | 0 8px 16px rgba(0,0,0,0.16) |

### Design Decorations

#### Dot Pattern Background
- Subtle dot pattern overlay on all screens
- Pattern: radial-gradient circles, 20px spacing
- Color: rgba(26, 35, 126, 0.06) on primary color
- Applied via `.dot-pattern` CSS class

#### Large Faded Icons
- Background icons positioned behind content
- Size: 100-120px
- Opacity: 0.05-0.06
- Color: Primary color (#1A237E)
- Used on: Welcome, Sign In, Sign Up, Home, Groups, Loans, Profile, Admin, Voting screens

#### Accent Strips on Cards
- 4px colored left border on key cards
- Variants:
  | Class | Color | Usage |
  |-------|-------|-------|
  | .card-accent | Primary (#1A237E) | General important cards |
  | .card-accent-success | Success (#4CAF50) | Loans qualify, KYC verified |
  | .card-accent-warning | Warning (#FF9800) | Pending requests |
  | .card-accent-gold | Accent (#FFD700) | Voting power, achievements |

---

# Screen Designs

## 1. Welcome / Get Started Screen (EXISTING)
**Status:** Already implemented in `activity_main.xml`

### Layout
- Full screen gradient background (#E3F2FD → #FFFFFF → #F5F5F5)
- Decorative circles (top-right, bottom-left) with 15% opacity
- **Dot pattern overlay** (radial gradient, 20px spacing, 6% opacity)
- **Large faded icon** (layers icon) positioned bottom-right
- Centered content container with 32px padding

### Components
1. **App Logo Container**
   - 120x120dp circular frame with gradient
   - Centered icon (80x80dp)

2. **Welcome Text**
   - "Welcome to" - 18sp, #666666, light weight

3. **App Name**
   - "Chama App" - 36sp, #1A237E, bold

4. **Tagline**
   - "Together We Save, Together We Grow" - 14sp, #888888, centered

5. **Get Started Button**
   - Full width (with 24dp horizontal margin)
   - Height: 56dp
   - Gradient background (#1A237E → #3949AB)
   - White text, 16sp, bold
   - 4dp elevation
   - Letter spacing: 0.1

6. **Sign In Link**
   - "Already have an account? Sign In"
   - 14sp, "Sign In" in #1A237E bold

---

## 2. Sign In Screen

### Layout
- Safe area aware (status bar, navigation bar)
- Scrollable content
- Gradient background (#E3F2FD → #FFFFFF → #F5F5F5)
- Decorative circles (top-right, bottom-left)
- **Dot pattern overlay** (radial gradient, 20px spacing, 6% opacity)
- **Large faded icon** (user icon) positioned top-right

### Components
1. **Back Button**
   - Top-left, 48x48dp touch target
   - Arrow icon, #1A237E

2. **Header Section**
   - "Welcome Back" - H1, #1A237E
   - "Sign in to continue" - Body, #666666

3. **Form Fields**
   - **Email/Phone Input**
     - Label: "Email or Phone" - Caption, #666666
     - Input field: 56dp height, white background, 12dp radius
     - Placeholder: "Enter your email or phone"
     - Left icon: envelope
     - Border: 1dp #E0E0E0, focus: #1A237E
   
   - **Password Input**
     - Label: "Password" - Caption, #666666
     - Input field: 56dp height, white background, 12dp radius
     - Placeholder: "Enter your password"
     - Left icon: lock
     - Right icon: eye toggle
     - Border: 1dp #E0E0E0, focus: #1A237E

4. **Forgot Password**
   - "Forgot Password?" - Body, #1A237E
   - Top-right aligned

5. **Sign In Button**
   - Full width with 24dp margins
   - Height: 56dp
   - Gradient (#1A237E → #3949AB)
   - "Sign In" - Button style, white

6. **Divider**
   - "OR" - Caption, #888888, centered
   - Horizontal lines (1dp, #E0E0E0) on sides

7. **Social Login**
   - Google button (outline style)
   - Apple button (outline style)
   - 48x48dp each

8. **Sign Up Link**
   - "Don't have an account? Sign Up" - Body
   - "Sign Up" in #1A237E bold

---

## 3. Sign Up / Register Screen

### Layout
- Safe area aware
- Scrollable content
- Gradient background (#E3F2FD → #FFFFFF → #F5F5F5)
- Decorative circles (top-right, bottom-left)
- **Dot pattern overlay** (radial gradient, 20px spacing, 6% opacity)
- **Large faded icon** (user-plus icon) positioned top-right

### Components
1. **Back Button** - Same as Sign In

2. **Header**
   - "Create Account" - H1, #1A237E
   - "Join your chama today" - Body, #666666

3. **Profile Picture**
   - 100x100dp circular frame
   - Camera icon overlay
   - "Add Photo" caption below

4. **Form Fields**
   - **Full Name** - Text input, "Enter your full name"
   - **Email** - Text input, "Enter your email"
   - **Phone Number** - Text input, "Enter phone number"
   - **Password** - Password input, "Create a password"
   - **Confirm Password** - Password input, "Confirm your password"

5. **Terms Checkbox**
   - Custom checkbox (24x24dp)
   - "I agree to the Terms of Service and Privacy Policy" - Caption
   - "Terms of Service" and "Privacy Policy" in #1A237E

6. **Sign Up Button**
   - Full width, 56dp height
   - Gradient background
   - "Create Account"

7. **Sign In Link**
   - "Already have an account? Sign In"

---

## 4. Home / Dashboard Screen

### Layout
- Bottom navigation bar (5 items)
- Scrollable content
- Pull-to-refresh
- Gradient background (#E3F2FD → #FFFFFF → #F5F5F5)
- **Dot pattern overlay** (radial gradient, 20px spacing, 6% opacity)
- **Large faded icon** (layers icon) positioned bottom-right
- User greeting: "Good Morning, Wambui Maina"

### Components
1. **Top Bar**
   - Greeting: "Good Morning, [Name]" - H2
   - Notification bell icon (red badge for count)
   - Profile picture (40dp circle)

2. **Balance Card**
   - "Total Savings" label - Caption
   - "KSh 125,000" - Display, #1A237E
   - "+12% this month" - Caption, Success color
   - "View Details" link
   - Background: White with shadow
   - 16dp radius

3. **Quick Actions Grid**
   - 2x2 grid with icons
   - **Join Group** - Icon: users-plus
   - **Create Group** - Icon: user-plus
   - **Apply Loan** - Icon: cash
   - **Transactions** - Icon: list
   - Each: 80x80dp, white background, 12dp radius

4. **My Groups Section**
   - Section header: "My Groups" - H3
   - "See All" link
   - Horizontal scrollable list of group cards

5. **Recent Transactions**
   - Section header: "Recent Activity" - H3
   - "See All" link
   - List of transaction items

6. **Bottom Navigation**
   - **Home** (active) - Icon: home-filled
   - **Groups** - Icon: users
   - **Loans** - Icon: cash
   - **Wallet** - Icon: wallet
   - **Profile** - Icon: user
   - Active: #1A237E, Inactive: #888888

---

## 5. Groups List Screen

### Layout
- Search bar at top
- Filter chips (All, My Groups, Public, Private)
- Scrollable grid of group cards
- FAB for creating new group
- Gradient background (#E3F2FD → #FFFFFF → #F5F5F5)
- **Dot pattern overlay** (radial gradient, 20px spacing, 6% opacity)
- **Large faded icon** (users icon) positioned bottom-right

### Components
1. **Search Bar**
   - "Search groups..." placeholder
   - Search icon left
   - Clear button right (when filled)
   - White background, 12dp radius

2. **Filter Chips**
   - Horizontal scroll
   - Chips: All, My Groups, Public, Private, Near Me
   - Active: filled #1A237E, white text
   - Inactive: outlined #666666

3. **Group Cards**
   - 2-column grid
   - Card: Image (16dp radius), Group name, Member count, Savings goal progress
   - Progress bar: #1A237E
   - "12 members" - Caption
   - "KSh 50,000 / 100,000" - Caption

4. **FAB**
   - 56dp, gradient background
   - Plus icon, white

---

## 6. Group Details Screen

### Layout
- Collapsing header with group image
- Tab layout (Overview, Members, Activity, Settings)
- Scrollable content
- Gradient background (#E3F2FD → #FFFFFF → #F5F5F5)
- **Dot pattern overlay** (radial gradient, 20px spacing, 6% opacity)

### Components
1. **Header Image**
   - 200dp height
   - Gradient overlay at bottom
   - Group name overlay (H1, white)
   - Back button (top-left)
   - Share/More options (top-right)

2. **Stats Row**
   - 4 items: Members, Savings, Loans, Votes
   - Icon + value + label per item

3. **Tab Layout**
   - Tabs: Overview, Members, Activity, Settings
   - Indicator: #1A237E, 3dp

4. **Overview Tab Content**
   - **Description** - Body text
   - **Rules** - Bullet list
   - **Next Meeting** - Card with date/time/location
   - **Admin** - User card with role badge

5. **Members Tab**
   - Member list (avatar, name, role badge, contribution amount)
   - Roles: Admin, Treasurer, Member

6. **Activity Tab**
   - Timeline of group activities
   - Icons for different activity types

7. **Settings Tab** (for admins)
   - Edit Group
   - Manage Members
   - Group Rules
   - Delete Group

---

## 7. Create/Join Group Screen

### Layout
- Step indicator (1-2-3)
- Form content
- Navigation buttons
- Gradient background (#E3F2FD → #FFFFFF → #F5F5F5)
- **Dot pattern overlay** (radial gradient, 20px spacing, 6% opacity)

### Components
1. **Step Indicator**
   - 3 steps: Details → Rules → Invite
   - Active step: #1A237E filled circle
   - Completed: #1A237E with checkmark
   - Upcoming: #E0E0E0 circle
   - Connecting lines

2. **Step 1: Group Details**
   - Group name input
   - Group description (multiline)
   - Group image upload
   - Category dropdown (Savings, Investment, Business)
   - Privacy toggle (Public/Private)

3. **Step 2: Group Rules**
   - Minimum contribution input
   - Contribution frequency dropdown (Daily, Weekly, Monthly)
   - Maximum loan amount input
   - Interest rate input
   - Number of guarantors required

4. **Step 3: Invite Members**
   - Phone number input (add multiple)
   - Import from contacts button
   - Share link button

5. **Navigation**
   - "Back" button (outline)
   - "Next" / "Create Group" button (filled gradient)

---

## 8. Members List Screen

### Layout
- Search bar
- Filter (All, Admins, Treasurers, Members)
- Member list
- Gradient background (#E3F2FD → #FFFFFF → #F5F5F5)
- **Dot pattern overlay** (radial gradient, 20px spacing, 6% opacity)

### Components
1. **Search** - "Search members..."

2. **Filter Chips** - All, Admins, Treasurers, Members

3. **Member Cards**
   - Avatar (48dp)
   - Name - Body, #1A237E
   - Role badge (Admin: gold, Treasurer: blue, Member: gray)
   - Phone number - Caption
   - Contribution amount - "KSh 5,000/mo"
   - Chevron right icon

4. **FAB** - "Add Member"

---

## 9. Member Details Screen

### Layout
- Gradient background (#E3F2FD → #FFFFFF → #F5F5F5)
- **Dot pattern overlay** (radial gradient, 20px spacing, 6% opacity)

### Components
1. **Profile Header**
   - Large avatar (100dp)
   - Name - H2
   - Phone number - Body, #666666
   - Role badge
   - "Member since" date

2. **Stats Cards** (horizontal scroll)
   - Total Contributed
   - Total Borrowed
   - Loans Outstanding
   - Voting Power

3. **Contributions Section**
   - List of contribution transactions
   - Date, amount, status

4. **Loans Section**
   - Active loans
   - Loan history

5. **Actions** (if admin)
   - Make Admin
   - Remove Member
   - Send Message

---

## 10. Loans Screen

### Layout
- Tab layout (Available, My Loans, History)
- Content based on active tab
- Gradient background (#E3F2FD → #FFFFFF → #F5F5F5)
- **Dot pattern overlay** (radial gradient, 20px spacing, 6% opacity)
- **Large faded icon** (dollar icon) positioned bottom-right

### Components
1. **Eligibility Banner**
   - "You qualify for KSh 50,000" - Success background
   - **Accent strip** (green, 4px left border) via `.card-accent-success`
   - "Apply Now" button

2. **Available Loans Tab**
   - Loan offer cards
   - Amount, interest rate, duration
   - "Apply" button

3. **My Loans Tab**
   - Active loans with progress
   - "KSh 20,000 / 50,000" - Progress bar
   - Next payment due date
   - "Pay" button

4. **History Tab**
   - Past loans list
   - Status badges (Paid, Defaulted)

---

## 11. Loan Application Screen

### Layout
- Gradient background (#E3F2FD → #FFFFFF → #F5F5F5)
- **Dot pattern overlay** (radial gradient, 20px spacing, 6% opacity)

### Components
1. **Loan Amount Slider**
   - Min to Max range
   - Current selection display
   - "KSh 10,000 - 100,000"

2. **Loan Details**
   - Duration dropdown (1, 2, 3, 6 months)
   - Purpose dropdown
   - Guarantor selection (from members)

3. **Summary Card**
   - Amount
   - Interest (e.g., 10%)
   - Total to repay
   - Monthly payment

4. **Terms Checkbox**
   - Accept terms

5. **Submit Button**
   - "Apply for Loan"

---

## 12. Wallet Screen

### Layout
- Balance card at top
- Action buttons
- Transaction list
- Gradient background (#E3F2FD → #FFFFFF → #F5F5F5)
- **Dot pattern overlay** (radial gradient, 20px spacing, 6% opacity)

### Components
1. **Balance Card**
   - "Available Balance" - Caption
   - "KSh 45,000" - Display
   - "KSh 5,000 pending" - Caption, Warning
   - Action buttons: Withdraw, Deposit

2. **Payment Methods**
   - M-Pesa, PayPal, Bank Account
   - Add new method button
   - Default badge on primary

3. **Quick Actions**
   - Send Money
   - Buy Airtime
   - Pay Bills
   - Withdraw

4. **Transactions List**
   - Grouped by date (Today, Yesterday, This Week)
   - Transaction items: Icon, title, subtitle, amount (+/-), status

---

## 13. Transactions History Screen

### Layout
- Filter chips (All, Credit, Debit)
- Date range picker
- Transaction list
- Gradient background (#E3F2FD → #FFFFFF → #F5F5F5)
- **Dot pattern overlay** (radial gradient, 20px spacing, 6% opacity)

### Components
1. **Filter Chips** - All, In, Out

2. **Date Range**
   - "This Month" dropdown
   - Custom range picker

3. **Transaction Items**
   - Icon (category-based)
   - Title
   - Subtitle (recipient/sender)
   - Amount (Green +/Red -)
   - Date/Time
   - Status (Success, Pending, Failed)

4. **Summary Card**
   - Total In
   - Total Out
   - Net

---

## 14. Profile Screen

### Layout
- Gradient background (#E3F2FD → #FFFFFF → #F5F5F5)
- **Dot pattern overlay** (radial gradient, 20px spacing, 6% opacity)
- **Large faded icon** (user icon) positioned top-right
- User name:
- Avatar initials:

### Components
1. **Profile Header**
   - Avatar (80dp) with edit overlay
   - Name - H2
   - Phone - Body
   - Email - Body
   - Edit Profile button

2. **Verification Status**
   - KYC badge (Verified/Pending/Not Started)
   - "Complete KYC" link if not verified

3. **Stats**
   - Groups joined
   - Total savings
   - Loans taken

4. **Menu Items**
   - My Groups
   - My Loans
   - Payment Methods
   - Notifications
   - Security
   - Help & Support
   - About
   - Log Out

---

## 15. Settings Screen

### Layout
- Gradient background (#E3F2FD → #FFFFFF → #F5F5F5)
- **Dot pattern overlay** (radial gradient, 20px spacing, 6% opacity)

### Components
1. **Account Section**
   - Edit Profile
   - Change Password
   - Change PIN

2. **Preferences**
   - Language
   - Currency
   - Dark Mode toggle

3. **Notifications**
   - Push Notifications toggle
   - SMS Notifications toggle
   - Email Notifications toggle

4. **Privacy**
   - Profile Visibility
   - Show Balance

5. **Support**
   - FAQ
   - Contact Us
   - Rate App

6. **Legal**
   - Terms of Service
   - Privacy Policy

7. **App Info**
   - Version number
   - Log Out

---

## 16. Admin Dashboard Screen

### Layout
- Scrollable dashboard
- Quick stats at top
- Charts and lists
- Gradient background (#E3F2FD → #FFFFFF → #F5F5F5)
- **Dot pattern overlay** (radial gradient, 20px spacing, 6% opacity)
- **Large faded icon** (shield icon) positioned bottom-right

### Components
1. **Stats Row**
   - Total Members
   - Total Savings
   - Active Loans
   - Pending Requests

2. **Pending Requests Cards**
   - Join requests
   - Loan applications
   - **Accent strip** (yellow/orange, 4px left border) via `.card-accent-warning`

5. **Quick Actions**
   - Approve Member
   - Review Loan
   - Send Announcement

---

## 17. Voting / Governance Screen

### Layout
- Gradient background (#E3F2FD → #FFFFFF → #F5F5F5)
- **Dot pattern overlay** (radial gradient, 20px spacing, 6% opacity)
- **Large faded icon** (check-circle icon) positioned bottom-right

### Components
1. **Active Proposals**
   - Proposal cards
   - Title, description
   - Time remaining
   - Vote buttons (For/Against/Abstain)
   - Progress bar

2. **Governance Token**
   - Token balance
   - Voting power
   - **Accent strip** (gold, 4px left border) via `.card-accent-gold`

---

## 18. Blockchain Transactions Screen

### Layout
- Gradient background (#E3F2FD → #FFFFFF → #F5F5F5)
- **Dot pattern overlay** (radial gradient, 20px spacing, 6% opacity)

### Components
1. **Wallet Connection Status**
   - Connected/Disconnected
   - Network (Polygon Testnet)
   - Wallet address

2. **Transaction History**
   - Hash, type, amount, status, timestamp
   - Link to block explorer

3. **Transaction Details Modal**
   - Full hash
   - Block number
   - Gas used
   - Status

---

## 19. KYC Verification Screen

### Layout
- Gradient background (#E3F2FD → #FFFFFF → #F5F5F5)
- **Dot pattern overlay** (radial gradient, 20px spacing, 6% opacity)

### Components
1. **Verification Status**
   - Step indicator: ID → Selfie → Verification
   - Current status display

2. **Verification Result**
   - **Accent strip** (green, 4px left border) via `.card-accent-success`
   - Success: "Verification Complete" with checkmark icon

---

## 20. Analytics Dashboard Screen

### Layout
- Gradient background (#E3F2FD → #FFFFFF → #F5F5F5)
- **Dot pattern overlay** (radial gradient, 20px spacing, 6% opacity)

### Components
1. **Time Range Selector**
   - Today, Week, Month, Year, Custom

2. **Key Metrics**
   - Total Revenue
   - Active Users
   - Transaction Volume
   - Growth Rate

3. **Charts**
   - Savings over time (Line)
   - Loan distribution (Pie)
   - User growth (Bar)
   - Top groups (Table)

4. **Export Button**
   - Export to PDF/CSV

---

## 21. System Settings (Admin)

### Components
1. **Group Settings**
   - Contribution amounts
   - Interest rates
   - Loan limits

2. **Fee Configuration**
   - Transaction fees
   - Loan interest
   - Withdrawal fees

3. **Feature Flags**
   - Toggle features on/off

4. **Backup & Restore**
   - Export data
   - Import data

---

## 21. Contribution / Payment Screen

### Layout
- Gradient background (#E3F2FD → #FFFFFF → #F5F5F5)
- **Dot pattern overlay** (radial gradient, 20px spacing, 6% opacity)

### Components
1. **Group Selector**
   - "Kilimani Savings" - Current group name

2. **Amount Input**
   - Contribution amount field
   - Default: KSh 5,000

3. **Payment Method**
   - M-Pesa, Bank, Card options

4. **Phone Number**
   - STK push phone number

5. **Summary**
   - Contribution amount
   - Processing fee
   - Total

---

## 22. Notifications Screen

### Layout
- Gradient background (#E3F2FD → #FFFFFF → #F5F5F5)
- **Dot pattern overlay** (radial gradient, 20px spacing, 6% opacity)

### Components
1. **Header**
   - "Notifications" title
   - "Mark all read" action

2. **Notification Groups**
   - Today
   - Yesterday
   - This Week

3. **Notification Types**
   - Contribution received (green icon)
   - New member request (primary icon)
   - Loan approved (warning icon)
   - Meeting reminder (primary icon)
   - Payment due (error icon)

---

# Figma Implementation Tips

## Components to Create in Figma

### Base Components
1. **Button** (Primary, Secondary, Outline, Text)
   - States: Default, Hover, Pressed, Disabled
   - Sizes: Large (56dp), Medium (48dp), Small (40dp)

2. **Input Field**
   - States: Default, Focused, Error, Disabled
   - With/without icons
   - With/without helper text

3. **Card**
   - Variants: Default, Elevated, Outlined

4. **Avatar**
   - Sizes: 32, 40, 48, 64, 80, 100dp
   - With/without status indicator

5. **Badge**
   - Types: Status, Count, Role

6. **Bottom Navigation**
   - 5 items with icons and labels
   - Active/Inactive states

7. **Tab Bar**
   - Scrollable variant

8. **Chip**
   - Filter and Input variants

9. **List Item**
   - With/without leading/trailing elements

10. **Progress Bar**
    - Linear and Circular variants

---

## Frame Specifications

### Mobile Frame
- Size: 375 x 812 dp (iPhone X)
- Safe area: 44dp top, 34dp bottom

### Naming Convention
- Use: `Screen Name / Component Name / State`
- Example: `Sign In / Button / Primary / Default`

### Color Styles
- Create color styles for all brand colors
- Use descriptive names (Primary, Primary-Light, Text-Primary, etc.)

### Text Styles
- Create text styles for all typography variants
- Use consistent naming (Display, H1, H2, Body, Caption, Button)
