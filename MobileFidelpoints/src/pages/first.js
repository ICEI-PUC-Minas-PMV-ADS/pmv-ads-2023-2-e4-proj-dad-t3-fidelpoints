import React, { useEffect } from 'react';
import {View, Text, StyleSheet, FlatList } from 'react-native';
import { List, Divider, Button, IconButton } from 'react-native-paper';
import { StatusBar } from 'expo-status-bar';
import Header from '../components/Header';
import { useNavigation, useRoute } from '@react-navigation/core';
import { useLocalSearchParams } from 'expo-router';

const DATA = [
    {
        ID: 1,
        produto: 'Copo Cooler',
        point: 200,
        status: "Aguardando"
    },
    {
        ID: 2,
        produto: 'Racket',
        point: 400,
        status: "Aguardando"
    },
    {
        ID:3,
        produto: 'Pipoqueira',
        point: 600,
        status: "Aguardando"
    }
];

const Item = ({title}) => (
    <View style={styles.item}>
      <Text style={styles.title}>{title}</Text>
    </View>
  );

export default function First() {

    const navigation = useNavigation();
//    const router = useRoute();
    const {nameLoja, id} = useLocalSearchParams()
//     async function callUser(){
//       try{
//         const {data} = api.get('/user', id);
//       }catch(e){
//         if(isAxiosResponse(e))
//         toast(e.response?.data)
//       }
//     }

// useEffect(()=>{
//   callUser()
// },[])

    // const {nameLoja} = router.params;
    const renderItem = ({item}) => (
      <>
      <List.Item
          left={() => 
            <List.Item 
            title ={item.produto} 
            style={{marginRight: 40}}/> 
          }
          title={item.point}
          
          right={() => <IconButton
            icon="arrow-right-bold"
            size={20}
            onPress={() => navigation.navigate('History', 
              {
                produto: item.produto, 
                status: item.status
              })}/>
            
          }
      />
        <Divider theme={{ colors: { primary: 'green' } }} />
    </>)
  return (

    <View style={styles.container}>
        <Header title = {nameLoja}/>
        <List.Item
          left={() =><List.Item 
            titleStyle={{fontWeight: 'bold'}}
            title='Produtos'
            style= {{  marginRight: 40}}
          />}
          titleStyle={{fontWeight: 'bold'}} 
          style={styles.listFirst} 
          title='Pontos' 
          right={() => 
            <List.Item 
              titleStyle={{fontWeight: 'bold'}} 
              title= 'Trocar'
            />
          }
          
        />
        <Divider style={{height: 3, backgroundColor: "#0025bf"}} />
        <View style={{color: '#000', height:10 }}/>
        <FlatList
        data={DATA}
        renderItem={renderItem}
        keyExtractor={item => item.ID}
      />
        <Button 
          icon="history"
          style={{width: 20}} 
          buttonColor='#0025bf' 
          mode="contained" 
          onPress={() => navigation.navigate("History")}>
        </Button>
        <StatusBar style="auto" />
    </View>
    
    );
}

const styles = StyleSheet.create({
    container: {
      flex: 1,
      backgroundColor: '#fff'
    },
    listFirst: {
        fontWeight: 'bold' ,
        marginTop: 50 }
  });
