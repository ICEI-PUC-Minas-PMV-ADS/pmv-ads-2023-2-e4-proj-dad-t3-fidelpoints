import React from 'react';
import { View } from 'react-native';
import { Appbar } from 'react-native-paper';
import First from './src/pages/first';
import { SafeAreaProvider } from 'react-native-safe-area-context';
import Rescue from './src/pages/Rescue';

export default function App() {

  return (
    <SafeAreaProvider>
    <Rescue/>
    </SafeAreaProvider>
  );
}


