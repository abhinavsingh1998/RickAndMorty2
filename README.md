# RickAndMorty

This project contains character list and character details of Rick and Morty.

## Features

- CharacterListFragment shows the list of characters 
- Pagination is included so that the app will keep fetching data for next page once we end up with last 6 characters in page
- On clicking on character user will be redirected to character Detail page
- User can also add favourite characters which will be stored in shared preferences

## Components Used

1. MVVM architecture.
2. Retrofit for networking calling.
3. Coroutine for async network call
4. Live data to store the Character Response and Character details.
5. Jetpack Navigation component is used to navigate between the fragments.
6. Shared Preferences to store character id as favourite.
7. Glide to load images.

## Future Scope

1. This project can be further optimised by using Hilt / Dagger for dependency injection
2. Unit test cases is pending.

## Screenshots

![Character List](https://github.com/abhinavsingh1998/RickAndMorty2/assets/88830847/e0e485c4-7d85-40bc-bee5-13e27647e856)

![Character Detail (FAV)](https://github.com/abhinavsingh1998/RickAndMorty2/assets/88830847/14a7ddfe-5d07-43e4-b662-00f67e802973)

![Character Detail (NON FAV)](https://github.com/abhinavsingh1998/RickAndMorty2/assets/88830847/499dbed5-fc62-4a50-b1d1-a3c37116f688)


