import React from 'react';
import { SafeAreaProvider } from 'react-native-safe-area-context';
import { NavigationContainer } from '@react-navigation/native';
import Home from './src/pages/home';
import Login from './src/pages/Login';

export default function App() {

  return (
    <NavigationContainer>
      <SafeAreaProvider>
        <Home/>
      </SafeAreaProvider>
    </NavigationContainer>
  );
}
