import React from 'react';
import { View } from 'react-native';
import { Appbar } from 'react-native-paper';
import First from './src/pages/first';
import { SafeAreaProvider } from 'react-native-safe-area-context';
import Rescue from './src/pages/Rescue';
import History from './src/pages/History';
import Store from './src/pages/Store';

export default function App() {

  return (
    <SafeAreaProvider>
    <History/>
    </SafeAreaProvider>
  );
}
