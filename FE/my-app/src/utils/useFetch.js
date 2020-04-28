import { useState, useEffect } from 'react';

const BASE_URL = 'http://52.79.117.147/api';

const getApiUrl = (categoryString, hash = null) => {
  if (hash) return `${BASE_URL}/${categoryString}/${hash}`;
  return `${BASE_URL}/menu/${categoryString}`;
};

export const API_URL = (type, hash = null) => {
  switch (type) {
    case 'main':
      return getApiUrl('main');
    case 'soup':
      return getApiUrl('soup');
    case 'side':
      return getApiUrl('side');
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
    if (!response.ok) throw new Error('Fetch Failed');
    setState(data);
    setIsLoading(true);
  };

  useEffect(() => {
    fetchData();
  }, []);

  return isLoading;
};
