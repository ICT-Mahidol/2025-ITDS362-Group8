/**
 * Setup Script - Create Test User for K6 Tests
 * Run this ONCE before running K6 tests
 * 
 * Usage:
 *   node setup-test-user.js
 */

const BACKEND_URL = 'http://localhost:9000';
const TEST_USER = {
  email: 'group8@store.local',
  password: 'test301408',
  first_name: 'Test',
  last_name: 'User Group 8',
};

async function createTestUser() {
  try {
    console.log('🔧 Creating test user for K6 tests...');
    console.log(`Email: ${TEST_USER.email}`);

    const response = await fetch(`${BACKEND_URL}/auth/customer/emailpass/register`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(TEST_USER),
    });

    if (response.ok) {
      const data = await response.json();
      console.log('✅ Test user created successfully!');
      console.log('Token:', data);
      console.log('\n📝 User credentials:');
      console.log(`   Email: ${TEST_USER.email}`);
      console.log(`   Password: ${TEST_USER.password}`);
      console.log('\n🚀 You can now run K6 tests!');
    } else {
      const error = await response.text();
      if (error.includes('already exists') || response.status === 409) {
        console.log('ℹ️  User already exists - you can proceed with testing');
      } else {
        console.error('❌ Failed to create user:');
        console.error(`   Status: ${response.status}`);
        console.error(`   Error: ${error}`);
      }
    }
  } catch (error) {
    console.error('❌ Error:', error.message);
    console.log('\n⚠️  Make sure Medusa backend is running on', BACKEND_URL);
  }
}

createTestUser();
