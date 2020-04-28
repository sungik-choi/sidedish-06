import { useState, useEffect } from 'react';

const BASE_URL = 'http://52.79.117.147';

const getApiUrl = (categoryString, hash = null) => {
  if (hash) return `${BASE_URL}/${categoryString}/${hash}`;
  return `${BASE_URL}/${categoryString}/`;
};

export const API_URL = (type, hash = null) => {
  switch (type) {
    case 'main':
      return getApiUrl('main', hash);
    case 'soup':
      return getApiUrl('soup', hash);
    case 'side':
      return getApiUrl('side', hash);
    case 'detail':
      return getApiUrl('detail', hash);
    default:
      throw new Error('Unvalid API');
  }
};

export const useFetch = (url, setState) => {
  const [isLoading, setIsLoading] = useState(false);

  const fetchData = async () => {
    const response = await fetch(url);
    const data = await response.json();
    setState(data);
    setIsLoading(true);
  };

  useEffect(() => {
    fetchData();
  }, []);

  return isLoading;
};
