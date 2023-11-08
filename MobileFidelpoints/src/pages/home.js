import React from 'react';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import First from './first';
import Rescue from './Rescue';
import Begin from './Begin';


const Stack = createNativeStackNavigator();

export default function Home() {
  return (
    <Stack.Navigator initialRouteName="Begin">
        <Stack.Screen 
          name="Begin" 
          component={Begin}
          options={{header:() => null}}
        />
        <Stack.Screen 
          name="Rescue" 
          component={Rescue}
          options={{header:() => null}}
        />
        <Stack.Screen 
          name="First" 
          component={First}
          options={{header:() => null}}
        />
      </Stack.Navigator>
  );
}