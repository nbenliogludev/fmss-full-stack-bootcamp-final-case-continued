"use client";

import { useQuery } from "@tanstack/react-query";
import { getAds } from "api/ad";
import React, { useState } from "react";

const realEstateImages = [
  "https://www.thedcteam.com/wp-content/uploads/2020/06/cta-2-5ee3dea6b4bcf.jpg",
  "https://photos.zillowstatic.com/fp/24b1eaa9ff65707729045b97e61f7e99-p_e.jpg",
  "https://images.ctfassets.net/n2ifzifcqscw/3QRMlAcJFrYAEAbhziixZW/d4b9aa50215c5ea7a161b8a6b59f1974/hero-real-estate-facts-trends.jpeg",
  "https://cdn.sdnews.com/wp-content/uploads/20240416064812/house-1836070_1280.jpg",
  "https://leadership.ng/wp-content/uploads/2024/05/luxury-residential-real-estate.png"
];

const getRandomImage = () => {
  const randomIndex = Math.floor(Math.random() * realEstateImages.length);
  return realEstateImages[randomIndex];
};

const formatCurrency = (amount: number) => {
  return new Intl.NumberFormat("en-US", {
    style: "currency",
    currency: "USD",
  }).format(amount);
};

const getStatusLabel = (status: string) => {
  switch (status) {
    case "ACTIVE":
      return "Active";
    case "PASSIVE":
      return "Passive";
    default:
      return status;
  }
};

const HomePage: React.FC = () => {
  const { data, isLoading, isError, error } = useQuery({
    queryKey: ["ads"],
    queryFn: getAds,
  });

  const [selectedStatus, setSelectedStatus] = useState<string>("all");

  if (isLoading) {
    return <p>Loading...</p>;
  }

  if (isError) {
    return <p>Error: {(error as Error).message}</p>;
  }

  const ads = data?.data || [];

  const filteredAds = selectedStatus === "all" ? ads : ads.filter((ad) => ad.status === selectedStatus);

  return (
    <div className="bg-gray-100 py-10">
      <div className="mx-auto max-w-2xl px-4 sm:px-6 lg:max-w-7xl lg:px-8">
        <h2 className="text-2xl font-extrabold tracking-tight text-gray-900">Real Estate Listings</h2>

        <div className="mt-6 mb-6">
          <label htmlFor="status" className="mr-2 text-gray-700">Filter by status:</label>
          <select
            id="status"
            value={selectedStatus}
            onChange={(e) => setSelectedStatus(e.target.value)}
            className="p-2 border rounded"
          >
            <option value="all">All</option>
            <option value="ACTIVE">Active</option>
            <option value="PASSIVE">Passive</option>
          </select>
        </div>

        <div className="grid grid-cols-1 gap-x-6 gap-y-10 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 xl:gap-x-8">
          {filteredAds.map((ad) => (
            <div key={ad.id} className="group bg-white shadow-lg rounded-lg overflow-hidden">
              <div className="h-48 w-full overflow-hidden">
                <img
                  alt={ad.description}
                  src={getRandomImage()} 
                  className="h-full w-full object-cover object-center group-hover:opacity-75"
                />
              </div>
              <div className="p-4">
                <h3 className="text-lg font-semibold text-gray-900">{ad.title}</h3>
                <p className="mt-2 text-xl font-bold text-green-600">{formatCurrency(ad.amount)}</p>
                <p className="mt-2 text-sm text-gray-600">{ad.description}</p>
                <p className="mt-2 text-xs text-gray-500">{getStatusLabel(ad.status)}</p>
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};

export default HomePage;
