"use client";

import { useQuery, useMutation, useQueryClient } from "@tanstack/react-query";
import { getMyAds, deleteAd } from "api/ad";
import { useSession } from "next-auth/react";
import React, { useState, Fragment } from "react";
import { Dialog, Transition } from "@headlessui/react";

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

const MyAdsPage: React.FC = () => {
  const { data: session } = useSession();
  const queryClient = useQueryClient();
  const { data, isLoading, isError, error } = useQuery({
    queryKey: ["ads"],
    queryFn: () => getMyAds(session?.user.id!),
  });

  const mutation = useMutation({
    mutationFn: (adId: number) => deleteAd(adId)
  });

  const [selectedStatus, setSelectedStatus] = useState<string>("all");
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [adToDelete, setAdToDelete] = useState<number | null>(null);

  const openModal = (adId: number) => {
    setAdToDelete(adId);
    setIsModalOpen(true);
  };

  const closeModal = () => {
    setIsModalOpen(false);
    setAdToDelete(null);
  };

  const handleDelete = () => {
    if (adToDelete !== null) {
      mutation.mutate(adToDelete);
      closeModal();
    }
  };

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
                <button
                  onClick={() => openModal(ad.id)}
                  className="mt-4 inline-flex items-center px-3 py-2 border border-transparent text-sm leading-4 font-medium rounded-md shadow-sm text-white bg-red-600 hover:bg-red-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-red-500"
                >
                  Delete
                </button>
              </div>
            </div>
          ))}
        </div>
      </div>

      <Transition appear show={isModalOpen} as={Fragment}>
        <Dialog as="div" className="relative z-10" onClose={closeModal}>
          <Transition.Child
            as={Fragment}
            enter="ease-out duration-300"
            enterFrom="opacity-0 scale-95"
            enterTo="opacity-100 scale-100"
            leave="ease-in duration-200"
            leaveFrom="opacity-100 scale-100"
            leaveTo="opacity-0 scale-95"
          >
            <div className="fixed inset-0 bg-black bg-opacity-25" />
          </Transition.Child>

          <div className="fixed inset-0 overflow-y-auto">
            <div className="flex min-h-full items-center justify-center p-4 text-center">
              <Transition.Child
                as={Fragment}
                enter="ease-out duration-300"
                enterFrom="opacity-0 scale-95"
                enterTo="opacity-100 scale-100"
                leave="ease-in duration-200"
                leaveFrom="opacity-100 scale-100"
                leaveTo="opacity-0 scale-95"
              >
                <Dialog.Panel className="w-full max-w-md transform overflow-hidden rounded-2xl bg-white p-6 text-left align-middle shadow-xl transition-all">
                  <Dialog.Title as="h3" className="text-lg font-medium leading-6 text-gray-900">
                    Confirm Deletion
                  </Dialog.Title>
                  <div className="mt-2">
                    <p className="text-sm text-gray-500">
                      Are you sure you want to delete this ad? This action cannot be undone.
                    </p>
                  </div>

                  <div className="mt-4">
                    <button
                      type="button"
                      className="inline-flex justify-center rounded-md border border-transparent bg-red-600 px-4 py-2 text-sm font-medium text-white hover:bg-red-700 focus:outline-none focus-visible:ring-2 focus-visible:ring-red-500 focus-visible:ring-offset-2"
                      onClick={handleDelete}
                    >
                      Delete
                    </button>
                    <button
                      type="button"
                      className="ml-2 inline-flex justify-center rounded-md border border-transparent bg-gray-300 px-4 py-2 text-sm font-medium text-gray-700 hover:bg-gray-400 focus:outline-none focus-visible:ring-2 focus-visible:ring-gray-500 focus-visible:ring-offset-2"
                      onClick={closeModal}
                    >
                      Cancel
                    </button>
                  </div>
                </Dialog.Panel>
              </Transition.Child>
            </div>
          </div>
        </Dialog>
      </Transition>
    </div>
  );
};

export default MyAdsPage;
